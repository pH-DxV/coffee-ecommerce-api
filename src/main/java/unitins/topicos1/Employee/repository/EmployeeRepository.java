package unitins.topicos1.Employee.repository;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import unitins.topicos1.Employee.model.Employee;

@ApplicationScoped
public class EmployeeRepository implements PanacheRepository<Employee> {
    
    public List<Employee> findByName(String name){

        return find("UPPER(nome) LIKE ?1", "%"+ name.toUpperCase() + "%").list();  

    }

    public Employee findByCpf(String cpf){

        return find("cpf = ?1", cpf).firstResult();

    }

    public Employee findByUsername(String username){

        return find("UPPER(user.username) = ?1", username.toUpperCase()).firstResult();

    }

    public Employee findByUsernameAndPassword(String username, String password){

        return find("user.username = ?1 AND user.password = ?2", username, password).firstResult();

    }

    public Employee findByIdUser(Long idUser) {

        return find("user.id = ?1", idUser).firstResult();

    }
    
}
