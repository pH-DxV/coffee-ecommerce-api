package unitins.topicos1.Telephone.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import unitins.topicos1.Telephone.model.Telephone;

@ApplicationScoped
public class TelephoneRepository implements PanacheRepository<Telephone>{
    
    public Telephone findByDdd(String ddd){
        return find("ddd = ?1", ddd).firstResult();
    }

    public Telephone findByPhoneNumber(String phoneNumber){
        return find ("ddd = ?1", phoneNumber).firstResult();
    }
}
