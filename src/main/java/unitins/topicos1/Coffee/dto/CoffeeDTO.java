package unitins.topicos1.Coffee.dto;

public record CoffeeDTO(

    String name,
    int quantity,
    Double weight,
    Double pricePurchase,
    Double priceSale,
    Long idMark,
    Long idCategory

) {
} 