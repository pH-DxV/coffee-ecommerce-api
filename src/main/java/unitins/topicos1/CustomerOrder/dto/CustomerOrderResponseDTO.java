package unitins.topicos1.CustomerOrder.dto;

import java.util.List;

import unitins.topicos1.Customer.dto.CustomerResponseDTO;
import unitins.topicos1.CustomerOrder.model.CustomerOrder;
import unitins.topicos1.OrderItem.dto.OrderItemResponseDTO;

public record CustomerOrderResponseDTO (

    Long id,
    CustomerResponseDTO customer,
    Double total,
    List<OrderItemResponseDTO> items

){

    public static CustomerOrderResponseDTO valueOf (CustomerOrder customerOrder){

        List<OrderItemResponseDTO> list = customerOrder.getItems()
                                            .stream()
                                            .map(OrderItemResponseDTO::valueOf)
                                            .toList();

        return new CustomerOrderResponseDTO(
            
            customerOrder.getId(),
            
            CustomerResponseDTO.valueOf(customerOrder.getCustomer()),
            
            customerOrder.getTotalValue(),
            
            list);
            
    }

}
