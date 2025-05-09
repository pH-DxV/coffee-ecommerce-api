package unitins.topicos1.Coffee.service;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;
import unitins.topicos1.Category.model.Category;
import unitins.topicos1.Category.repository.CategoryRespository;
import unitins.topicos1.Coffee.dto.CoffeeDTO;
import unitins.topicos1.Coffee.dto.CoffeeResponseDTO;
import unitins.topicos1.Coffee.model.Coffee;
import unitins.topicos1.Coffee.repository.CoffeeRepository;
import unitins.topicos1.Mark.model.Mark;
import unitins.topicos1.Mark.repository.MarkRepository;
import unitins.topicos1.Weight.repository.WeightRepository;

@ApplicationScoped
public class CoffeeServiceImpl implements CoffeeService {

    @Inject
    public CoffeeRepository repository;

    @Inject
    public MarkRepository markRepository;

    @Inject
    public CategoryRespository categoryRepository;

    @Inject
    public WeightRepository weightRepository;


    @Override
    @Transactional
    public CoffeeResponseDTO create(@Valid CoffeeDTO dto) {

        Mark mark = markRepository.findById(dto.idMark());
        if (mark == null) {
            throw new NotFoundException("Mark not found with id: " + dto.idMark());
        }

        Category category = categoryRepository.findById(dto.idCategory());
        if (category == null) {
            throw new NotFoundException("Category not found with id: " + dto.idCategory());
        }
        
        Coffee coffee = new Coffee();

        coffee.setName(dto.name());
        coffee.setQuantity(dto.quantity());
        coffee.setWeight(dto.weight());
        coffee.setPricePurchase(dto.pricePurchase());
        coffee.setPriceSale(dto.priceSale());
        coffee.setMark(markRepository.findById(dto.idMark()));
        coffee.setCategory(categoryRepository.findById(dto.idCategory()));

        repository.persist(coffee);

        return CoffeeResponseDTO.valueOf(coffee);
    }

    @Override
    @Transactional
    public void update(Long id, CoffeeDTO dto) {

        Coffee coffee = repository.findById(id);
        
        if (coffee == null) {
            throw new NotFoundException("Coffee not found with id: " + id);
        }

        Mark mark = markRepository.findById(dto.idMark());
        if (mark == null) {
            throw new NotFoundException("Mark not found with id: " + dto.idMark());
        }

        Category category = categoryRepository.findById(dto.idCategory());
        if (category == null) {
            throw new NotFoundException("Category not found with id: " + dto.idCategory());
        }


        coffee.setName(dto.name());
        coffee.setQuantity(dto.quantity());
        coffee.setWeight(dto.weight());
        coffee.setPricePurchase(dto.pricePurchase());
        coffee.setPriceSale(dto.priceSale());
        coffee.setMark(markRepository.findById(dto.idMark()));
        coffee.setCategory(categoryRepository.findById(dto.idCategory()));

    }

    @Override
    @Transactional
    public void updateStock(Long id, int qntPurchased) {
        // FAZER PATH DE ESTOQUE PRODUTO
        Coffee coffee = repository.findById(id);

        coffee.setQuantity(coffee.getQuantity() - qntPurchased);

    }

    @Override
    public CoffeeResponseDTO findById(Long id) {

        Coffee coffee = repository.findById(id);

        if (coffee != null)

            return CoffeeResponseDTO.valueOf(coffee);

        return null;
        
    }

    @Override
    public List<CoffeeResponseDTO> findAll(int page, int pageSize) {
        
        List<Coffee> list = repository.findAll()
                                        .page(page,pageSize)
                                        .list();

        return list.stream()
                    .map(CoffeeResponseDTO::valueOf)
                    .collect(Collectors.toList());

    }

    @Override
    public List<CoffeeResponseDTO> findAll() {

        return repository.listAll()
                            .stream()
                            .map(CoffeeResponseDTO::valueOf)
                            .toList();

    }

    @Override
    @Transactional
    public boolean delete(Long id) {

        return repository.deleteById(id);

    }



    
}
