package unitins.topicos1.CustomerOrder.repository;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import unitins.topicos1.CustomerOrder.model.CustomerOrder;

@ApplicationScoped
public class CustomerOrderRepository implements PanacheRepository<CustomerOrder>{
    
    public List<CustomerOrder> findByCustomer (Long idCustomer){

        return find("customer.id = ?1", idCustomer).list();
        
    }

}
