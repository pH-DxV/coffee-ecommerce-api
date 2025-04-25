package unitins.topicos1.Customer.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import unitins.topicos1.DefaultEntity.model.DefaultEntity;
import unitins.topicos1.Telephone.model.Telephone;
import unitins.topicos1.User.model.User;

@Entity
@Data
public class Customer extends DefaultEntity {

    @Column(name = "cpf", unique = true, nullable = false)
    private String cpf;

    @Column(name = "name")
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_telephone", nullable =  false)
    private Telephone telephone;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_customer")
    private List<Address> addressList;

    @OneToOne
    @JoinColumn(name = "id_user", unique = true, nullable = false)
    private User user;


    
}
