package unitins.topicos1.Coffee.service;

import java.util.List;

import jakarta.validation.Valid;
import unitins.topicos1.Coffee.dto.CoffeeDTO;
import unitins.topicos1.Coffee.dto.CoffeeResponseDTO;

public interface CoffeeService {
    
    public CoffeeResponseDTO create(@Valid CoffeeDTO dto);

    public boolean delete(Long id);

    public void update(Long id, CoffeeDTO dto);

    public CoffeeResponseDTO findById(Long id);

    public List<CoffeeResponseDTO> findAll(int page, int pageSize);

    public List<CoffeeResponseDTO> findAll();

    public void updateStock(Long id, int quantityPurchased);


}
