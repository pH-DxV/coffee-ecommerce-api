package unitins.topicos1.CustomerOrder.dto;

import java.util.List;

import unitins.topicos1.OrderItem.dto.OrderItemDTO;

public record CustomerOrderDTO(

    Long idCustomer,
    List<OrderItemDTO> items


) {
} 