package edu.miu.cs545.spring.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Role {
    @Id
    String role;
    String description;
    @ManyToMany(cascade = CascadeType.ALL)
    List<User> users;
}
