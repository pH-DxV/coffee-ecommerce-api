package unitins.topicos1.Address.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import unitins.topicos1.Address.model.Address;

@ApplicationScoped
public class AddressRepository implements PanacheRepository<Address> {

    public Address findByCep(String cep){

        return find("cep = ?1", cep).firstResult();
        
    }
    
}
