package unitins.topicos1.Telephone.service;

import java.util.List;

import jakarta.validation.Valid;
import unitins.topicos1.Telephone.dto.TelephoneDTO;
import unitins.topicos1.Telephone.dto.TelephoneResponseDTO;

public interface TelephoneService {
    
    public TelephoneResponseDTO create (@Valid TelephoneDTO dto);

    public void update (Long id, TelephoneDTO dto);

    public boolean delete(Long id);

    public List<TelephoneResponseDTO> findAll();

    public TelephoneResponseDTO findById(Long id);

    public TelephoneResponseDTO findByDdd(String ddd);

    public TelephoneResponseDTO findByPhoneNumber(String phoneNumber);
}
