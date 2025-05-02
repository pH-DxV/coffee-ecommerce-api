package unitins.topicos1.Customer.dto;

import jakarta.validation.constraints.NotBlank;
import unitins.topicos1.Address.dto.AddressDTO;

public record CustomerAddAddressDTO(

    @NotBlank
    AddressDTO newAddress

) {
    
} 