package unitins.topicos1.Telephone.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import unitins.topicos1.Telephone.dto.TelephoneDTO;
import unitins.topicos1.Telephone.dto.TelephoneResponseDTO;
import unitins.topicos1.Telephone.model.Telephone;
import unitins.topicos1.Telephone.repository.TelephoneRepository;

@ApplicationScoped
public class TelephoneServiceImpl implements TelephoneService {

    @Inject
    TelephoneRepository repository;

    @Override
    @Transactional
    public TelephoneResponseDTO create(@Valid TelephoneDTO dto){

        Telephone telephone = new Telephone();

        telephone.setDdd(dto.ddd());
        telephone.setPhoneNumber(dto.phoneNumber());

        repository.persist(telephone);

        return TelephoneResponseDTO.valueof(telephone);

    }

    @Override
    @Transactional
    public void update (Long id, TelephoneDTO dto){

        Telephone telephone = repository.findById(id);

        telephone.setDdd(dto.ddd());
        telephone.setPhoneNumber(dto.phoneNumber());
        
    }



    @Override
    @Transactional
    public boolean delete(Long id) {

        return repository.deleteById(id);

    }

    @Override
    public List<TelephoneResponseDTO> findAll() {

        return repository.findAll()
                .stream()
                .map(e -> TelephoneResponseDTO.valueof(e)).toList();
    
    }

    @Override
    public TelephoneResponseDTO findById(Long id) {

        Telephone supplier = repository.findById(id);

        if(supplier != null)

            return TelephoneResponseDTO.valueof(supplier);

        return null;

    }

    @Override
    public TelephoneResponseDTO findByDdd(String ddd) {

        Telephone telephone = repository.findByDdd(ddd);

        if(telephone != null)

            return TelephoneResponseDTO.valueof(telephone);

        return null;

    }

    @Override
    public TelephoneResponseDTO findByPhoneNumber(String phoneNumber) {

        Telephone telephone = repository.findByPhoneNumber(phoneNumber);

        if(telephone != null)

            return TelephoneResponseDTO.valueof(telephone);

        return null;
    }

}
