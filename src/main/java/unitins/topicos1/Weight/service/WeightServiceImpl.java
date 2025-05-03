package unitins.topicos1.Weight.service;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import unitins.topicos1.Weight.dto.WeightDTO;
import unitins.topicos1.Weight.dto.WeightResponseDTO;
import unitins.topicos1.Weight.model.Weight;
import unitins.topicos1.Weight.repository.WeightRepository;

@ApplicationScoped
public class WeightServiceImpl implements WeightService {

    @Inject
    WeightRepository repository;

    @Override
    @Transactional
    public WeightResponseDTO create(@Valid WeightDTO dto) {

        Weight weight = new Weight();

        weight.setWeightValue(dto.weightValue());
        weight.setUnitOfMeasurement(dto.unitOfMeasurement());
        weight.setPackageType(dto.packageType());
        weight.setDescription(dto.description());

        repository.persist(weight);

        return WeightResponseDTO.valueOf(weight);

    }

    @Override
    @Transactional
    public void update(Long id, WeightDTO dto) {

        Weight weight = repository.findById(id);
        
        weight.setUnitOfMeasurement(dto.unitOfMeasurement());
        weight.setPackageType(dto.packageType());
        weight.setDescription(dto.description());

    }

    @Override
    public WeightResponseDTO findById(Long id) {

        Weight weight = repository.findById(id);

        if (weight != null){

            return WeightResponseDTO.valueOf(weight);
        }
        
        return null;

    }

    @Override
    public List<WeightResponseDTO> findAll(int page, int pageSize) {

        return repository.findAll()
                            .page(page, pageSize)
                            .list()
                            .stream()
                            .map(WeightResponseDTO::valueOf)
                            .collect(Collectors.toList());

    }

    @Override
    public List<WeightResponseDTO> findAll() {

        return repository.listAll()
                            .stream()
                            .map(WeightResponseDTO::valueOf)
                            .toList();

    }

    @Override
    public long count() {

        return repository.count();

    }

    @Override
    @Transactional
    public boolean delete(Long id) {

        return repository.deleteById(id);

    }

    @Override
    public List<WeightResponseDTO> findByWeightValue(Double value) {

        return repository.findByWeightValue(value)
                            .list()
                            .stream()
                            .map(WeightResponseDTO::valueOf)
                            .toList();

    }

    @Override
    public List<WeightResponseDTO> findByUnitOfMeasurement(String unit) {
        
        return repository.findByUnitOfMeasurement(unit)
                            .stream()
                            .map(WeightResponseDTO::valueOf)
                            .toList();

    }

    @Override
    public List<WeightResponseDTO> findByPackageType(String packageType) {
        return repository.findByPackageType(packageType)
                            .stream()
                            .map(WeightResponseDTO::valueOf)
                            .toList();

    }
    
}
