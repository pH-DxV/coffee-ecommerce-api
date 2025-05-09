package unitins.topicos1.Category.service;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import unitins.topicos1.Category.dto.CategoryDTO;
import unitins.topicos1.Category.dto.CategoryResponseDTO;
import unitins.topicos1.Category.model.Category;
import unitins.topicos1.Category.repository.CategoryRespository;


@ApplicationScoped
public class CategoryServiceImpl implements CategoryService{
    

    @Inject
    CategoryRespository repository;

    @Override
    @Transactional
    public CategoryResponseDTO create(@Valid CategoryDTO dto) {

        Category category = new Category();

        category.setName(dto.name());
        category.setDescription(dto.description());
        category.setRoastLevel(dto.roastLevel());
        category.setOrigin(dto.origin());

        repository.persist(category);

        return CategoryResponseDTO.valueof(category);

    }

    @Override
    @Transactional
    public boolean delete(Long id) {

        return repository.deleteById(id);

    }

    @Override
    @Transactional
    public void update(Long id, CategoryDTO dto) {

        Category category = repository.findById(id);

        category.setName(dto.name());
        category.setDescription(dto.description());
        category.setRoastLevel(dto.roastLevel());
        category.setOrigin(dto.origin());
        
    }

    @Override
    public List<CategoryResponseDTO> findAll(int page, int pageSize) {

        List<Category> list = repository.findAll()
                                        .page(page,pageSize)
                                        .list();

        return list.stream()
                    .map(CategoryResponseDTO::valueof).collect(Collectors.toList());

    }

    @Override
    public List<CategoryResponseDTO> findAll(){

        List<Category> lista = repository.findAll().list();

        return lista.stream().map(CategoryResponseDTO::valueof).toList();

    }

    @Override
    public long count(){

        return repository.count();

    }

    @Override
    public CategoryResponseDTO findById(Long id) {

        Category category = repository.findById(id);

        if(category != null)

            return CategoryResponseDTO.valueof(repository.findById(id));

        return null;       

    }


    @Override
    public List<CategoryResponseDTO> findByName(String name) {

        List<Category> listCategory = repository.findByName(name)
                                                .list();

        return listCategory.stream()
                            .map(CategoryResponseDTO::valueof)
                            .toList();

    }
    
    @Override
    public List<CategoryResponseDTO> findByName(int page, int pageSize, String name) {

        List<Category> listCategory = repository.findByName(name)
                                                .page(page, pageSize)
                                                .list();

        return listCategory.stream()
                            .map(CategoryResponseDTO::valueof)
                            .toList(); 

    }

    @Override
    public CategoryResponseDTO findByFullName(String name) {

       return CategoryResponseDTO.valueof(repository.findByFullName(name));

    }

    @Override
    public List<CategoryResponseDTO> findByDescription(String description) {
        
        return repository.findByDescription(description)
                         .stream()
                         .map(CategoryResponseDTO::valueof).toList();

    }

    @Override
    public List<CategoryResponseDTO> findByRoastLevel(String roastLevel) {

        return repository.findByRoastLevel(roastLevel)
                         .stream()
                         .map(CategoryResponseDTO::valueof).toList();

    }

    @Override
    public List<CategoryResponseDTO> findByOrigin(String origin) {

       return repository.findByOrigin(origin)
                         .stream()
                         .map(CategoryResponseDTO::valueof).toList();

    }
}
