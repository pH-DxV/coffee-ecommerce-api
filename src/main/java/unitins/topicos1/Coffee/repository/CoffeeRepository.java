package unitins.topicos1.Coffee.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import unitins.topicos1.Coffee.model.Coffee;

@ApplicationScoped
public class CoffeeRepository implements PanacheRepository<Coffee>{
    
}
