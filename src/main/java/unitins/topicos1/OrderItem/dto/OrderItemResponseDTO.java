package unitins.topicos1.OrderItem.dto;

import unitins.topicos1.OrderItem.model.OrderItem;

public record OrderItemResponseDTO(

    Long id,

    Double productValue,

    Integer quantity

) {

    public static OrderItemResponseDTO valueOf(OrderItem item){

        return new OrderItemResponseDTO(

            item.getId(),
            item.getValue(),
            item.getQuantity();

        )    

    }

}
