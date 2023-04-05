package edu.miu.cs545.spring.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
public class PostDto {
    Long id;
    String title;
    String content;
}
