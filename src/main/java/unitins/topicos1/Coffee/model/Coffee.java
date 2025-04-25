package unitins.topicos1.Coffee.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import unitins.topicos1.DefaultEntity.model.DefaultEntity;


@Entity
public class Coffee extends DefaultEntity{
    
    @Column(name = "name")
    private String name;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "price_purchase")
    private Double pricePurchase;

    @Column(name = "price_sale")
    private Double priceSale;

    @ManyToOne
    @JoinColumn(name = "id_mark")
    private Mark mark;

    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category category;




    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getPricePurchase() {
        return pricePurchase;
    }

    public void setPricePurchase(Double pricePurchase) {
        this.pricePurchase = pricePurchase;
    }

    public Double getPriceSale() {
        return priceSale;
    }

    public void setPriceSale(Double priceSale) {
        this.priceSale = priceSale;
    }

    public Mark getMark() {
        return mark;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }



    
}
