package unitins.topicos1.Category.repository;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import unitins.topicos1.Category.model.Category;

@ApplicationScoped
public class CategoryRespository implements PanacheRepository<Category>{

    public PanacheQuery<Category> findByName (String name){

        return find("UPPER(name) LIKE ?1", "%" + name.toUpperCase() + "%");

    }
    
    public Category findByFullName(String name){
        
        return find("UPPER(name) LIKE ?1", "%"+ name.toUpperCase() + "%").firstResult();

    }

    public List<Category> findByDescription(String description){

        return find("UPPER(description) = ?1", "%"+ description.toUpperCase() + "%").firstResult();

    }

    public List<Category> findByRoastLevel(String roastLevel){

        return find("UPPER(roast_level) = ?1", "%"+ roastLevel.toUpperCase() + "%").firstResult();
    }

    public List<Category> findByOrigin(String origin){

        return find("UPPER(origin) = ?1", "%"+ origin.toUpperCase() + "%").firstResult();

    }


}
