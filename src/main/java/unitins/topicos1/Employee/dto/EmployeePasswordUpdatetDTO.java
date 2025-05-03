package unitins.topicos1.Employee.dto;

import jakarta.validation.constraints.NotBlank;

public record EmployeePasswordUpdatetDTO
(

    @NotBlank
    String oldPassword,

    @NotBlank
    String newPassword

) {}
