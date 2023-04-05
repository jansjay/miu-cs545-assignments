package edu.miu.cs545.spring.models;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String title;
    String content;
}
