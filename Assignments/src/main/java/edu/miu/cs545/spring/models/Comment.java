package edu.miu.cs545.spring.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    @ManyToOne
    Post post;
}
