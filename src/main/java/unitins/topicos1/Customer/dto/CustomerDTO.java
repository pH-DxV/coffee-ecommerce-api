package unitins.topicos1.Customer.dto;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import unitins.topicos1.Address.dto.AddressDTO;

public record CustomerDTO(

    // Customer
    @NotBlank
    String name,
    List<AddressDTO> addressList,
    String cpf,
    LocalDate dateOfBirth,

    // Telephone
    String ddd,
    String phoneNumber,

    // User
    @NotBlank
    String username,

    @NotBlank
    String password

){}

