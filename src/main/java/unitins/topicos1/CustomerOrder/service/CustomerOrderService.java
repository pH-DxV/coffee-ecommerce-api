package unitins.topicos1.CustomerOrder.service;

import java.util.List;

import jakarta.validation.Valid;
import unitins.topicos1.CustomerOrder.dto.CustomerOrderDTO;
import unitins.topicos1.CustomerOrder.dto.CustomerOrderResponseDTO;

public interface CustomerOrderService {

    public CustomerOrderResponseDTO create(@Valid CustomerOrderDTO dto)   ;

    public CustomerOrderResponseDTO findById(Long id);

    public List<CustomerOrderResponseDTO> findAll();

    public List<CustomerOrderResponseDTO> findByCustomer(Long idCustomer);

    


}