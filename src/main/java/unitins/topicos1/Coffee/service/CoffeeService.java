package unitins.topicos1.Coffee.service;

import java.util.List;

import jakarta.validation.Valid;
import unitins.topicos1.Coffee.dto.CoffeeDTO;
import unitins.topicos1.Coffee.dto.CoffeeResponseDTO;

public interface CoffeeService {
    
    public CoffeeResponseDTO create(@Valid CoffeeDTO dto);

    public boolean delete(Long id);

    public void update(Long id, CoffeeDTO);

    public CoffeeResponseDTO findById(Long id);

    public List<CoffeeResponseDTO> findAll(int page, int pageSize);

    public List<CoffeeResponseDTO> findAll();

    public CoffeeResponseDTO saveImage(Long id, String imageName);

    public void updateStock(Long id, int quantityPurchased);


}
