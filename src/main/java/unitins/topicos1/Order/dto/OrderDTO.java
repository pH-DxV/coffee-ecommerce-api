package unitins.topicos1.Order.dto;

import java.util.List;

public record OrderDTO(

    Long idCustomer,
    List<CustomerItemDTO> items

) {
} 