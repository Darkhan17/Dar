package kz.dar.internship.clientapi.repository;


import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Table(name = "client")
public class ClientEntity {
    @Id
    @GeneratedValue
    private long id;
    @Column
    private String name;

    @Column
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
