package unitins.topicos1.Address.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import unitins.topicos1.Address.dto.AddressDTO;
import unitins.topicos1.Address.dto.AddressResponseDTO;
import unitins.topicos1.Address.model.Address;
import unitins.topicos1.Address.repository.AddressRepository;

@ApplicationScoped
public class AddressServiceImpl implements AddressService{
    
    @Inject
    AddressRepository repository;

    @Override
    @Transactional
    public AddressResponseDTO create (@Valid AddressDTO dto){

        Address address = new Address();

        address.setCep(dto.cep());
        address.setStreet(dto.street());
        address.setComplement(dto.complement());

        repository.persist(address);

        return AddressResponseDTO.valueOf(address);

    }

    @Override
    @Transactional
    public void update(Long id, AddressDTO dto) {

        Address address = repository.findById(id);

        address.setCep(dto.cep());
        address.setStreet(dto.street());
        address.setComplement(dto.complement());

    }

    @Override
    @Transactional
    public boolean delete(Long id) {

        return repository.deleteById(id);

    }

    @Override
    public List<AddressResponseDTO> findAll() {

        return repository.findAll()
                            .stream()
                            .map(AddressResponseDTO::valueOf)
                            .toList();

    }

    @Override
    public AddressResponseDTO findById(Long id) {

        Address fornecedor = repository.findById(id);

        if(fornecedor != null){

            return AddressResponseDTO.valueOf(fornecedor);

        }

        return null;

    }

    @Override
    public AddressResponseDTO findByCep(String cep) {

        Address address = repository.findByCep(cep);

        if(address != null){

            return AddressResponseDTO.valueOf(address);

        }

        return null;

    }


}
