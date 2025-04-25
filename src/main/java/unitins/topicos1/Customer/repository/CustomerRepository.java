package unitins.topicos1.Customer.repository;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import unitins.topicos1.Customer.model.Customer;

@ApplicationScoped
public class CustomerRepository implements PanacheRepository<Customer>{

    public List<Customer> findByName(String name){

        return find("UPPER (name) LIKE ?1", "%" + name.toUpperCase() + "%").list();

    }
    
    public Customer findByCpf(String cpf){

        return find("cpf = ?1", cpf).firstResult();

    }

    public Customer findByUsername(String username){

        return find("user.username = ?1", username).firstResult();
    
    }

    public Customer findByUsernameAndPassword(String username, String password){
       
        return find("user.username = ?1 AND usuario.password = ?2", username, password).firstResult();
   
    }

    public Customer findByIdUser(Long idUser){

        return find("user.id = ?1", idUser.firstResult();
  
    }


}
