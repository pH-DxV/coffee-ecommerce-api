package unitins.topicos1.Order.dto;

import java.util.List;

import unitins.topicos1.OrderItem.dto.OrderItemDTO;

public record OrderDTO(

    Long idCustomer,
    List<OrderItemDTO> items


) {
} 