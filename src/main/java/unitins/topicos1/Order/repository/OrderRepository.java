package unitins.topicos1.Order.repository;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import unitins.topicos1.Order.model.Order;

@ApplicationScoped
public class OrderRepository implements PanacheRepository<Order>{
    
    public List<Order> findByCustomer (Long idCustomer){

        return find("customer.id = ?1", idCustomer).list();
        
    }

}
