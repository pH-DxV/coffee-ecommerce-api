package unitins.topicos1.Mark.repository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import unitins.topicos1.Mark.model.Mark;

@ApplicationScoped
public class MarkRepository implements PanacheRepository<Mark>{

    public PanacheQuery<Mark> findByName(String name){

        return find ("UPPER(name) LIKE ?1", "%" + name.toUpperCase() + "%");

    }

    public Mark findByFullName(String name){

        return find("UPPER(name) LIKE ?1", "%" + name.toUpperCase() + "%").firstResult();
        
    }
    
}
