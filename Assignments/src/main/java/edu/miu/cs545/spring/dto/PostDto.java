package edu.miu.cs545.spring.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
public class PostDto {
    long id;
    String title;
    String content;
    String author;
}
