package unitins.topicos1.Order.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import unitins.topicos1.Coffee.model.Coffee;
import unitins.topicos1.Coffee.repository.CoffeeRepository;
import unitins.topicos1.Coffee.service.CoffeeService;
import unitins.topicos1.Customer.repository.CustomerRepository;
import unitins.topicos1.Order.dto.OrderDTO;
import unitins.topicos1.Order.dto.OrderResponseDTO;
import unitins.topicos1.Order.model.Order;
import unitins.topicos1.Order.repository.OrderRepository;
import unitins.topicos1.OrderItem.dto.OrderItemDTO;
import unitins.topicos1.OrderItem.model.OrderItem;
import unitins.topicos1.validation.ValidationException;

@ApplicationScoped
public class OderServiceImpl implements OrderService{
    
    @Inject
    public OrderRepository repository;

    @Inject

    public CoffeeRepository coffeeRepository;

    @Inject

    public CoffeeService coffeeService;

    @Inject
    public CustomerRepository customerRepository;


    @Override
    @Transactional
    public OrderResponseDTO create(@Valid OrderDTO dto) {

        Order order = new Order();

        order.setDate(LocalDateTime.now());
        order.setCustomer(customerRepository.findById(dto.idCustomer()));
        order.setTotalValue(0d);

        List<OrderItem> items = new ArrayList<OrderItem>();

        for (OrderItemDTO itemDTO : dto.items()){

            Coffee coffeItem = coffeeRepository.findById(itemDTO.idCoffe());

            if (coffeItem.getQuantity() >= itemDTO.quantity()){

                OrderItem itemUnit = new OrderItem();

                itemUnit.setQuantity(itemDTO.quantity());

                // Calc Total w/o Discount
                itemUnit.setValue(coffeItem.getPriceSale());

                items.add(itemUnit);
                
                // Order Total Value
                order.setTotalValue( (order.getTotalValue() + (itemUnit.getValue() * itemUnit.getQuantity() ) ) );

                // CoffeeService.updateStock(itemDTO.idCoffee(), itemDTO.quantity());


            }else{

                throw new ValidationException("Insufficient Stock",
                                                "Insufficient Stock of the product: "
                                                + coffeItem.getName()
                                                + "\n"
                                                + "Product Stock: "
                                                + coffeItem.getQuantity());
                                                                                        
            }


        }
        
        order.setItems(items);

        repository.persist(order);

        return OrderResponseDTO.valueOf(order);

    }

    @Override
    public OrderResponseDTO findById (Long id){

        Order order= repository.findById (id);

        if ( order != null)

            return OrderResponseDTO.valueOf(order);

        return null;

    }

    @Override
    public List<OrderResponseDTO> findAll() {

        return repository
                .listAll()
                .stream()
                .map(OrderResponseDTO::valueOf).toList();

    }

    @Override
    public List<OrderResponseDTO> findByCustomer (Long idCustomer){

        List<OrderResponseDTO> list = repository
                                        .findByCustomer(idCustomer)
                                        .stream()
                                        .map(e -> OrderResponseDTO.valueOf(e)).toList();
        
        if (list != null){

            return list;
        
        }

        return null;

    }

}
