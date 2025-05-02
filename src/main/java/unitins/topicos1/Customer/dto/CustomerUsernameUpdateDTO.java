package unitins.topicos1.Customer.dto;

import jakarta.validation.constraints.NotBlank;

public record CustomerUsernameUpdateDTO(

    @NotBlank
    String newUsername

){}