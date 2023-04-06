package edu.miu.cs545.spring.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
//Postgres cannot create user table
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    List<Post> posts;
}
