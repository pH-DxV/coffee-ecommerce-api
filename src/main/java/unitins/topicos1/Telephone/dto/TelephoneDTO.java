package unitins.topicos1.Telephone.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TelephoneDTO (
    
    @NotBlank(message = "DDD cannot be null or empty!")
    @Size(min = 2, max = 2, message = "DDD size must be 2 characters long!")
    String ddd,

    @NotBlank(message = "The Phone Number cannot be null or empty!")
    String phoneNumber
    
){
    
}
