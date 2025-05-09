package unitins.topicos1.Category.service;

import java.util.List;

import jakarta.validation.Valid;
import unitins.topicos1.Category.dto.CategoryDTO;
import unitins.topicos1.Category.dto.CategoryResponseDTO;

public interface CategoryService {

    
    public CategoryResponseDTO create(@Valid CategoryDTO dto);

    public void update(Long id, CategoryDTO dto);

    public boolean delete(Long id);

    public long count();

    public CategoryResponseDTO findById(Long id);

    public List<CategoryResponseDTO> findByName(String name);

    public List<CategoryResponseDTO> findByName(int page, int pageSize, String name);

    public List<CategoryResponseDTO> findAll(int page, int pageSize);

    public List<CategoryResponseDTO> findAll();

    public CategoryResponseDTO findByFullName(String name);

    public List<CategoryResponseDTO> findByDescription(String description);

    public List<CategoryResponseDTO> findByRoastLevel(String roastLevel);

    public List<CategoryResponseDTO> findByOrigin(String origin);

}
