package unitins.topicos1.Customer.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import unitins.topicos1.Address.dto.AddressResponseDTO;
import unitins.topicos1.Customer.model.Customer;
import unitins.topicos1.Telephone.dto.TelephoneResponseDTO;

public record CustomerResponseDTO(

    Long id,
    String name,
    TelephoneResponseDTO telephone,
    LocalDate dateOfBirth,
    String cpf,
    String username,
    String password,
    LocalDateTime registrationDate,
    LocalDateTime changeDate,
    List<AddressResponseDTO> addressList

){

    public static CustomerResponseDTO valueOf (Customer customer){

        List<AddressResponseDTO> list = customer.getAddressList()
                                                .stream()
                                                .map(AddressResponseDTO::valueOf)
                                                .toList();

        return new CustomerResponseDTO(customer.getId(),
                                        customer.getName(),
                                        TelephoneResponseDTO.valueof(customer.getTelephone()),
                                        customer.getDateOfBirth(),
                                        customer.getCpf(),
                                        customer.getUser().getUsername(),
                                        customer.getUser().getPassword(),
                                        customer.getDateRegister(),
                                        customer.getDateChange(),
                                        list
                                    );

    }

}