package edu.miu.cs545.spring.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String title;
    String content;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    List<Comment> comments;
}
