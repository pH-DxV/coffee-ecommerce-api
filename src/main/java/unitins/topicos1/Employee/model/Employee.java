package unitins.topicos1.Employee.model;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import unitins.topicos1.DefaultEntity.model.DefaultEntity;
import unitins.topicos1.Telephone.model.Telephone;
import unitins.topicos1.User.model.User;

@Entity
public class Employee extends DefaultEntity{

    @Column(name = "admission_code")
    private String admissionCode;

    @Column(name = "admission_date", nullable = false)
    private LocalDate admissionDate;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "cpf", unique = true, nullable = false)
    private String cpf;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_telephone", nullable = false)
    private Telephone telephone;

    @OneToOne
    @JoinColumn(name = "id_user", unique = true, nullable = false)
    private User user;

    


    public String getAdmissionCode() {
        return admissionCode;
    }
    public void setAdmissionCode(String admissionCode) {
        this.admissionCode = admissionCode;
    }
    public LocalDate getAdmissionDate() {
        return admissionDate;
    }
    public void setAdmissionDate(LocalDate admissionDate) {
        this.admissionDate = admissionDate;
    }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Telephone getTelephone() {
        return telephone;
    }
    public void setTelephone(Telephone telephone) {
        this.telephone = telephone;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    

    

    
}
