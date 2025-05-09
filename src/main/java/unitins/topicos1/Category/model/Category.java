package unitins.topicos1.Category.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import unitins.topicos1.DefaultEntity.model.DefaultEntity;

@Entity
public class Category extends DefaultEntity{
    

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "roast_level")
    private String roastLevel;

    @Column(name = "origin")
    private String origin;



    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getRoastLevel() {
        return roastLevel;
    }
    public void setRoastLevel(String roastLevel) {
        this.roastLevel = roastLevel;
    }
    public String getOrigin() {
        return origin;
    }
    public void setOrigin(String origin) {
        this.origin = origin;
    }

}
