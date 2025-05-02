package unitins.topicos1.OrderItem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import unitins.topicos1.Coffee.model.Coffee;
import unitins.topicos1.DefaultEntity.model.DefaultEntity;

@Entity
public class OrderItem extends DefaultEntity{

    private Double value;
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private Coffee coffee;

    
    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Coffee getCoffee() {
        return coffee;
    }

    public void setCoffee(Coffee coffee) {
        this.coffee = coffee;
    }
    
    

    
}
