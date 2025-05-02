package unitins.topicos1.Address.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AddressDTO(

    @NotBlank(message = "CEP cannot be null or empty.")
    @Size(min = 8, max = 8, message = "CEP must have 8 characters.")
    String cep,

    @NotBlank(message = "Street cannot be null or empty")
    String street,

    @NotBlank(message = "Complement cannot be null or empty")
    String complement

) {}