package unitins.topicos1.Telephone.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import unitins.topicos1.DefaultEntity.model.DefaultEntity;

@Entity
public class Telephone extends DefaultEntity {

    @Column(name = "ddd", nullable = false)
    private String ddd;

    @Column(name =  "number", nullable = false)
    private String phoneNumber;

    //  === GET & SET ===
    public String getDdd() {
        return ddd;
    }
    public void setDdd(String ddd) {
        this.ddd = ddd;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    
}
