package unitins.topicos1.Telephone.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import unitins.topicos1.DefaultEntity.model.DefaultEntity;

@Entity
@Data
public class Telephone extends DefaultEntity {

    @Column(name = "ddd", nullable = false)
    private String ddd;

    @Column(name =  "number", nullable = false)
    private String phoneNumber;
    
}
