package unitins.topicos1.Mark.service;

import java.util.List;

import jakarta.validation.Valid;
import unitins.topicos1.Mark.dto.MarkDTO;
import unitins.topicos1.Mark.dto.MarkResponseDTO;

public interface MarkService {

    public MarkResponseDTO create(@Valid MarkDTO dto);
   
    public void update(Long id, MarkDTO dto);
   
    public boolean delete(Long id);
   
    public MarkResponseDTO findById(Long id);
   
    public List<MarkResponseDTO> findByName(String name);
   
    public List<MarkResponseDTO> findAll(int page, int pageSize);
   
    public List<MarkResponseDTO> findAll();
   
    public List<MarkResponseDTO> findByName(int page, int pageSize, String name);
   
    public long count();

}