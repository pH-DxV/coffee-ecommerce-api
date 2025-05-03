package unitins.topicos1.Order.dto;

import java.util.List;

import unitins.topicos1.Customer.dto.CustomerResponseDTO;
import unitins.topicos1.Order.model.Order;
import unitins.topicos1.OrderItem.dto.OrderItemResponseDTO;

public record OrderResponseDTO (

    Long id,
    CustomerResponseDTO customer,
    Double total,
    List<OrderItemResponseDTO> items

){

    public static OrderResponseDTO valueOf (Order order){

        List<OrderItemResponseDTO> list = order.getItems()
                                            .stream()
                                            .map(OrderItemResponseDTO::valueOf)
                                            .toList();

        return new OrderResponseDTO(
            
            order.getId(),
            
            CustomerResponseDTO.valueOf(order.getCustomer()),
            
            order.getTotalValue(),
            
            list);
            
    }

}
