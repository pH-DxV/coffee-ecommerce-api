package unitins.topicos1.Coffee.dto;

import unitins.topicos1.Category.dto.CategoryResponseDTO;
import unitins.topicos1.Coffee.model.Coffee;
import unitins.topicos1.Mark.dto.MarkResponseDTO;

public record CoffeeResponseDTO(

    Long id,
    String name,
    int quantity,
    Double weight,
    Double pricePurchase,
    Double priceSale,
    MarkResponseDTO mark,
    CategoryResponseDTO category

) {

    public static CoffeeResponseDTO valueOf(Coffee coffee){

        return new CoffeeResponseDTO
            (

            coffee.getId(),
            coffee.getName(),
            coffee.getQuantity(),
            coffee.getWeight(),
            coffee.getPricePurchase(),
            coffee.getPriceSale(),
            MarkResponseDTO.valueOf(coffee.getMark()),
            CategoryResponseDTO.valueof(coffee.getCategory())

            );

    }

}