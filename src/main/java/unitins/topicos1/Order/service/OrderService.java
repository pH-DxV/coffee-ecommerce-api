package unitins.topicos1.Order.service;

import java.util.List;

import jakarta.validation.Valid;
import unitins.topicos1.Order.dto.OrderDTO;
import unitins.topicos1.Order.dto.OrderResponseDTO;

public interface OrderService {

    public OrderResponseDTO create(@Valid OrderDTO dto)   ;

    public OrderResponseDTO findById(Long id);

    public List<OrderResponseDTO> findAll();

    public List<OrderResponseDTO> findByCustomer(Long idCustomer);

    


}