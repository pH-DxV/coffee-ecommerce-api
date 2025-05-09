package unitins.topicos1.CustomerOrder.service;

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
import unitins.topicos1.CustomerOrder.dto.CustomerOrderDTO;
import unitins.topicos1.CustomerOrder.dto.CustomerOrderResponseDTO;
import unitins.topicos1.CustomerOrder.model.CustomerOrder;
import unitins.topicos1.CustomerOrder.repository.CustomerOrderRepository;
import unitins.topicos1.OrderItem.dto.OrderItemDTO;
import unitins.topicos1.OrderItem.model.OrderItem;
import unitins.topicos1.validation.ValidationException;

@ApplicationScoped
public class CustomerOderServiceImpl implements CustomerOrderService{
    
    @Inject
    public CustomerOrderRepository repository;

    @Inject
    public CoffeeRepository coffeeRepository;

    @Inject
    public CoffeeService coffeeService;

    @Inject
    public CustomerRepository customerRepository;


    @Override
    @Transactional
    public CustomerOrderResponseDTO create(@Valid CustomerOrderDTO dto) {

        CustomerOrder customerOrder = new CustomerOrder();

        customerOrder.setDate(LocalDateTime.now());
        customerOrder.setCustomer(customerRepository.findById(dto.idCustomer()));
        customerOrder.setTotalValue(0d);

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
                customerOrder.setTotalValue( (customerOrder.getTotalValue() + (itemUnit.getValue() * itemUnit.getQuantity() ) ) );

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
        
        customerOrder.setItems(items);

        repository.persist(customerOrder);

        return CustomerOrderResponseDTO.valueOf(customerOrder);

    }

    @Override
    public CustomerOrderResponseDTO findById (Long id){

        CustomerOrder customerOrder= repository.findById (id);

        if ( customerOrder != null)

            return CustomerOrderResponseDTO.valueOf(customerOrder);

        return null;

    }

    @Override
    public List<CustomerOrderResponseDTO> findAll() {

        return repository
                .listAll()
                .stream()
                .map(CustomerOrderResponseDTO::valueOf).toList();

    }

    @Override
    public List<CustomerOrderResponseDTO> findByCustomer (Long idCustomer){

        List<CustomerOrderResponseDTO> list = repository
                                        .findByCustomer(idCustomer)
                                        .stream()
                                        .map(e -> CustomerOrderResponseDTO.valueOf(e)).toList();
        
        if (list != null){

            return list;
        
        }

        return null;

    }

}
