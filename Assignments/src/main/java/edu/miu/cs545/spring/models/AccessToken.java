package edu.miu.cs545.spring.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class AccessToken {
    @Id
    String token;
    @OneToOne
    User user;
    @ManyToOne(cascade = CascadeType.ALL)
    RefreshToken refreshToken;
}
