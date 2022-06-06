package no.ntnu.xxs.user;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    private Integer id;

    @Column(nullable = false, length = 50, unique = true)
    private String name;

    public Role() { }

    public Role(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Role(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role(Integer id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return this.name;
    }

}