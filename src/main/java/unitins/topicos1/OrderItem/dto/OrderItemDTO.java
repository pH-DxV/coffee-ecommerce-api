package unitins.topicos1.OrderItem.dto;

import jakarta.validation.constraints.NotBlank;

public record OrderItemDTO(

    @NotBlank(message = "Choose a coffe quantity!")
    Integer quantity,

    @NotBlank(message = "Pick a coffee")
    Long idCoffe


) {
}