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

   


}
