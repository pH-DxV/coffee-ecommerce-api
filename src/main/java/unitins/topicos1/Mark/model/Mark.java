package unitins.topicos1.Mark.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import unitins.topicos1.DefaultEntity.model.DefaultEntity;

@Entity
public class Mark extends DefaultEntity{

    @Column(name = "name")
    private String name;
    
    @Column(name = "logo")
    private String logo;


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLogo() {
        return logo;
    }
    public void setLogo(String logo) {
        this.logo = logo;
    }
    
}
