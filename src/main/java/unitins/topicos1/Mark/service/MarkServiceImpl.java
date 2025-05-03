package unitins.topicos1.Mark.service;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import unitins.topicos1.Mark.dto.MarkDTO;
import unitins.topicos1.Mark.dto.MarkResponseDTO;
import unitins.topicos1.Mark.model.Mark;
import unitins.topicos1.Mark.repository.MarkRepository;
import unitins.topicos1.validation.ValidationException;

@ApplicationScoped
public class MarkServiceImpl implements MarkService{
    
    @Inject
    MarkRepository repository;

    @Override
    @Transactional
    public MarkResponseDTO create(@Valid MarkDTO dto){

        Mark mark = new Mark();

        mark.setName(dto.name());
        mark.setLogo(dto.logo());

        repository.persist(mark);

        return MarkResponseDTO.valueOf(mark);

    }

    public void verifyName(String name){

        Mark mark = repository.findByFullName(name);

        if (mark != null){

            throw new ValidationException("name", "The name '"+ name +"' has already been used");
        }

    }

    @Override
    @Transactional
    public boolean delete (Long id){

        return repository.deleteById(id);

    }

    @Override
    @Transactional
    public void update(Long id, MarkDTO dto){

        Mark mark = repository.findById(id);

        mark.setName(dto.name());
        mark.setLogo(dto.logo());
        
    }

    @Override
    public List<MarkResponseDTO> findAll (int page, int pageSize){

        List<Mark> list = repository
                            .findAll()
                            .page(page,pageSize)
                            .list();

        return list.stream()
                    .map(MarkResponseDTO::valueOf).collect(Collectors.toList());

    }

    @Override
    public List<MarkResponseDTO> findAll(){

        List<Mark> list = repository.findAll().list();

        return list.stream().map(MarkResponseDTO::valueOf).toList();

    }

    @Override
    public long count(){

        return repository.count();

    }

    @Override
    public MarkResponseDTO findById(Long id){

        Mark mark = repository.findById(id);

        if (mark != null){

            return MarkResponseDTO.valueOf(mark);

        }

        return null;

    }

    @Override
    public List<MarkResponseDTO> findByName(String name){

        List<Mark> markList = repository
                                .findByName(name)
                                .list();

        return markList
                    .stream()
                    .map(MarkResponseDTO::valueOf)
                    .toList();
    }

    @Override
    public List<MarkResponseDTO> findByName(int page, int pageSize, String name){

        List<Mark> markList = repository
                                .findByName(name)
                                .page(page, pageSize)
                                .list();

        return markList
                .stream()
                .map(MarkResponseDTO::valueOf)
                .toList();

    }

}
