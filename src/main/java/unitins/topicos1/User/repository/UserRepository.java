package unitins.topicos1.User.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import unitins.topicos1.User.model.User;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User>{
    
    public User findByUser (String username){

        return find("username = ?1", username).firstResult();
    }

}
