package unitins.topicos1.Weight.service;

import java.util.List;

import jakarta.validation.Valid;
import unitins.topicos1.Weight.dto.WeightDTO;
import unitins.topicos1.Weight.dto.WeightResponseDTO;

public interface WeightService {

    public WeightResponseDTO create(@Valid WeightDTO dto);
    
    public boolean delete(Long id);
    
    public void update(Long id, WeightDTO dto);
    
    public WeightResponseDTO findById(Long id);
    
    public List<WeightResponseDTO> findAll(int page, int pageSize);
    
    public List<WeightResponseDTO> findAll();
    
    public long count();
    
    public List<WeightResponseDTO> findByWeightValue(Double value);
    
    public List<WeightResponseDTO> findByUnitOfMeasurement(String unit);
    
    public List<WeightResponseDTO> findByPackageType(String packageType);
    
}
