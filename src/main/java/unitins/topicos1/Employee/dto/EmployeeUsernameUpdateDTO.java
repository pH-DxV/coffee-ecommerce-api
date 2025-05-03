package unitins.topicos1.Employee.dto;

import jakarta.validation.constraints.NotBlank;

public record EmployeeUsernameUpdateDTO
(

    @NotBlank
    String newUsername

) {
}
