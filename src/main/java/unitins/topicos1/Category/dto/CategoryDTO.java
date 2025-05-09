package unitins.topicos1.Category.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoryDTO
(

    @NotBlank(message = "Name cannot be null or empty")
    String name,

    @Size(min = 10, max = 200)
    @NotBlank(message = "Description cannot be null or empty")
    String description,

    @NotBlank(message = "Roast Level cannot be null or empty")
    String roastLevel,

    @NotBlank(message = "Origin cannot be null or empty")
    String origin

) {
    
    

}
