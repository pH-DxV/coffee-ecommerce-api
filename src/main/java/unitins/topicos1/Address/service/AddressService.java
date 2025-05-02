package unitins.topicos1.Address.service;

import java.util.List;

import jakarta.validation.Valid;
import unitins.topicos1.Address.dto.AddressDTO;
import unitins.topicos1.Address.dto.AddressResponseDTO;

public interface AddressService {

    public AddressResponseDTO create (@Valid AddressDTO dto);

    public void update (Long id, AddressDTO dto);

    public boolean delete (Long id);

    public List<AddressResponseDTO> findAll();

    public AddressResponseDTO findById (Long id);

    public AddressResponseDTO findByCep (String cep);
    
    
}