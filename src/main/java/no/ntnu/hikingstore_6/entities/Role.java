package no.ntnu.hikingstore_6.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 45)
    private String name;

    //Constructor with name only
    public Role(String name) {
        this.name = name;
    }

    //Constructor with ID only
    public Role(Long id) {
        this.id = id;
    }

    //Full constructor
    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    //Empty constructor for hibernate
    public Role() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
