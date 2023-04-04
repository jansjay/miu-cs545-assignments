package edu.miu.cs545.spring.models;

import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostV2 {
    long id;
    String title;
    String content;
    String author;
}