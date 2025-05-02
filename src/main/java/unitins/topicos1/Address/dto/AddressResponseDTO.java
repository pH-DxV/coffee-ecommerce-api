package unitins.topicos1.Address.dto;

import unitins.topicos1.Address.model.Address;

public record AddressResponseDTO(

    Long id,
    String cep,
    String street,
    String complement

) {

    public static AddressResponseDTO valueOf (Address address){

        return new AddressResponseDTO(

        address.getId(),
        address.getCep(),
        address.getStreet(),
        address.getComplement()

        );
        
    }

}