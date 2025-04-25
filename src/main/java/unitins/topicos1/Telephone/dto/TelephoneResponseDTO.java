package unitins.topicos1.Telephone.dto;

import unitins.topicos1.Telephone.model.Telephone;

public record TelephoneResponseDTO (

    Long id,
    String ddd,
    String phoneNumber

){

    public static TelephoneResponseDTO valueof(Telephone telephone){

        return new TelephoneResponseDTO(

            telephone.getId(),
            telephone.getDdd(),
            telephone.getPhoneNumber()

        );
        
    }

}


