package unitins.topicos1.DefaultEntity.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;


@MappedSuperclass
public class DefaultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_register")
    private LocalDateTime dateRegister;

    @Column(name = "data_change")
    private LocalDateTime dateChange;

    @PrePersist
    public void recordRegisterDate(){
        setDateRegister(LocalDateTime.now());
    }

    @PreUpdate
    public void recordChangeDate(){
        setDateChange(LocalDateTime.now());
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LocalDateTime getDateRegister() {
        return dateRegister;
    }
    public void setDateRegister(LocalDateTime dateRegister) {
        this.dateRegister = dateRegister;
    }
    public LocalDateTime getDateChange() {
        return dateChange;
    }
    public void setDateChange(LocalDateTime dateChange) {
        this.dateChange = dateChange;
    }

    



}
