package unitins.topicos1.Customer.dto;

import java.util.List;

import unitins.topicos1.Address.dto.AddressResponseDTO;
import unitins.topicos1.Customer.model.Customer;

public record CustomerAllAddressResponseDTO(

    List<AddressResponseDTO> addressList

) {

    public static CustomerAllAddressResponseDTO valueof(Customer customer){
        
        List<AddressResponseDTO> list = customer.getAddressList()
                                                .stream()
                                                .map(AddressResponseDTO::valueOf)
                                                .toList();

        return new CustomerAllAddressResponseDTO(list);

    }
    
}