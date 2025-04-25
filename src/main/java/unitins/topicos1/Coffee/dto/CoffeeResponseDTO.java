package unitins.topicos1.Coffee.dto;

import unitins.topicos1.Coffee.model.Coffee;

public record CoffeeResponseDTO(

    Long id,
    String name,
    int quantity,
    Double weight,
    Double pricePurchase,
    Double priceSale,
    MarkResponseDTO mark,
    CategoryResponseDTO category,


) {

    public static CoffeeResponseDTO valueOf(Coffee coffee){

        return new CoffeeResponseDTO(
            coffee.getId(),
            coffee.getName(),
            coffee.getQuantity(),
            coffee.getWeight(),
            coffee.getPricePurchase(),
            coffee.getPriceSale(),
            /*
            MarkResponseDTO.valueOf(coffe.getMark()),
            CategoryResponseDTO.valueOf(coffe.getCategory());


            */


    }
}