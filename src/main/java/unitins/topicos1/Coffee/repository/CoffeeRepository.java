package unitins.topicos1.Coffee.repository;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import unitins.topicos1.Coffee.model.Coffee;

@ApplicationScoped
public class CoffeeRepository implements PanacheRepository<Coffee>{
 
    public Coffee findById(Long id) {

        return getEntityManager().find(Coffee.class, id);

    }

    public PanacheQuery<Coffee> findAllPaged(int page, int pageSize) {

        return findAll().page(page, pageSize);

    }

    public List<Coffee> listAllCoffees() {

        return listAll();

    }

    public boolean deleteById(Long id) {

        return delete("id = ?1", id) == 1;

    }

    public List<Coffee> findByCategory(Long categoryId) {

        return list("category.id", categoryId);

    }

    public List<Coffee> findByMark(Long markId) {

        return list("mark.id", markId);

    }

    public List<Coffee> findByName(String name) {

        return list("LOWER(name) LIKE LOWER(?1)", "%" + name + "%");

    }

}
