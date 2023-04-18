package edu.miu.cs545.spring.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
public class PostDto {
    Long id;
    String title;
    String content;
    //Note: We have to do additional processing due to the
    //      assignment requirement of having unidirectional relationship
    Long userId;

    UserDto user;
}
