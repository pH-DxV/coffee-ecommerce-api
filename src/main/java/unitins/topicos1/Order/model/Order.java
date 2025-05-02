package unitins.topicos1.Order.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import unitins.topicos1.Customer.model.Customer;
import unitins.topicos1.DefaultEntity.model.DefaultEntity;
import unitins.topicos1.OrderItem.model.OrderItem;

@Entity
public class Order extends DefaultEntity{
    
    private LocalDateTime date;
    private Double totalValue;

   @ManyToOne
   @JoinColumn(name = "id_customer")
   private Customer customer;

   @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
   @JoinColumn(name = "id_order")
    private List<OrderItem> items;




   public LocalDateTime getDate() {
    return date;
   }
   public void setDate(LocalDateTime date) {
    this.date = date;
   }
   public Double getTotalValue() {
    return totalValue;
   }
   public void setTotalValue(Double totalValue) {
    this.totalValue = totalValue;
   }
   public Customer getCustomer() {
    return customer;
   }
   public void setCustomer(Customer customer) {
    this.customer = customer;
   }
   public List<OrderItem> getItems() {
    return items;
   }
   public void setItems(List<OrderItem> items) {
    this.items = items;
   }


    

   


}
