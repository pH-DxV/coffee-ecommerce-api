package unitins.topicos1.Weight.repository;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import unitins.topicos1.Weight.model.Weight;

@ApplicationScoped
public class WeightRepository implements PanacheRepository<Weight> {

    public PanacheQuery<Weight> findByDescription(String description) {
        return find("UPPER(description) LIKE ?1", "%" + description.toUpperCase() + "%");
    }

    public PanacheQuery<Weight> findByWeightValue(Double weightValue) {
        return find("weightValue = ?1", weightValue);  
    }

    public Weight findByWeightValueFirstResult(Double weightValue) {
        return find("weightValue = ?1", weightValue).firstResult();
    }

    public List<Weight> findByUnitOfMeasurement(String unitOfMeasurement) {
        return find("unitOfMeasurement LIKE ?1", "%" + unitOfMeasurement + "%").list();  
    }

    // Método adicional para buscar por tipo de embalagem
    public List<Weight> findByPackageType(String packageType) {
        return find("UPPER(packageType) LIKE ?1", "%" + packageType.toUpperCase() + "%").list();
    }
    
}
