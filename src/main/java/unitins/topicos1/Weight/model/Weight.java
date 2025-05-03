package unitins.topicos1.Weight.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import unitins.topicos1.DefaultEntity.model.DefaultEntity;

@Entity
public class Weight extends DefaultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "weight_value")
    private Double weightValue;

    @Column(name = "unit_of_measurement")
    private String unitOfMeasurement; // Ex: "kg", "g", "lb"

    @Column(name = "package_type")
    private String packageType; // Ex: "Saco", "Caixa", "Pacote"

    @Column(name = "description")
    private String description; // Descrição adicional



    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Double getWeightValue() {
        return weightValue;
    }
    public void setWeightValue(Double weightValue) {
        this.weightValue = weightValue;
    }
    public String getUnitOfMeasurement() {
        return unitOfMeasurement;
    }
    public void setUnitOfMeasurement(String unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }
    public String getPackageType() {
        return packageType;
    }
    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
}