package unitins.topicos1.Employee.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;

public record EmployeeDTO
(

    // USER
    @NotBlank
    String username,

    @NotBlank
    String password,

    // Employee
    @NotBlank
    String name,

    @NotBlank
    String cpf,

    String ddd,
    String phoneNumber,

    LocalDate dateOfBirth,
    String admissionCode,
    LocalDate admissionDate

){}
