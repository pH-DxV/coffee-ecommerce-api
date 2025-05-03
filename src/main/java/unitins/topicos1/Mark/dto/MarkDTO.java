package unitins.topicos1.Mark.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MarkDTO(

    @NotBlank(message = "Name cannot be null or empty")
    @Size(min = 2, max = 60, message = "The name length must be between 2 and 60 characters")
    String name,
    String logo

) {
    
}
