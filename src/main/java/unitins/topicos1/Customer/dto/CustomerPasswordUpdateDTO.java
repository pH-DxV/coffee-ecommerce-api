package unitins.topicos1.Customer.dto;

import jakarta.validation.constraints.NotBlank;

public record CustomerPasswordUpdateDTO(

    @NotBlank
    String oldPassword,

    @NotBlank
    String newPassword
    
) {}