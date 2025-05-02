package unitins.topicos1.Customer.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;

public record CustomerUpdateDTO(

    //Customer
    @NotBlank
    String name,
    String cpf,
    LocalDate dateOfBirth,

    //Telephone
    String ddd,
    String phoneNumber

){}
        