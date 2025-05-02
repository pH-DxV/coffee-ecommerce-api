package unitins.topicos1.Address.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import unitins.topicos1.DefaultEntity.model.DefaultEntity;

@Entity
public class Address extends DefaultEntity {


    @Column(name = "cep")
    private String cep;

    @Column(name = "street")
    private String street;

    @Column(name = "complement")
    private String complement;


    public String getCep() {
        return cep;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public String getComplement() {
        return complement;
    }
    public void setComplement(String complement) {
        this.complement = complement;
    }
    
}
