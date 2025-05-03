package unitins.topicos1.Employee.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import unitins.topicos1.Employee.model.Employee;
import unitins.topicos1.Telephone.dto.TelephoneResponseDTO;
import unitins.topicos1.User.dto.UserResponseDTO;

public record EmployeeResponseDTO
(

    Long id,
    String name,
    TelephoneResponseDTO telephone,
    LocalDate dateOfBirth,
    String cpf,
    String admissionCode,
    LocalDate admissionDate,
    UserResponseDTO user,
    LocalDateTime dateRegister,
    LocalDateTime dateChange

) {
    
    public static EmployeeResponseDTO valueof(Employee employee){

        return new EmployeeResponseDTO
        (

            employee.getId(),
            employee.getName(),
            TelephoneResponseDTO.valueof(employee.getTelephone()),
            employee.getDateOfBirth(),
            employee.getCpf(),
            employee.getAdmissionCode(), 
            employee.getAdmissionDate(),
            UserResponseDTO.valueOf(employee.getUser()),
            employee.getDateRegister(), 
            employee.getDateChange()

        );

    }
    
}    

