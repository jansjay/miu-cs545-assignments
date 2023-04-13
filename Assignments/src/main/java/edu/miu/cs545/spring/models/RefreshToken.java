package edu.miu.cs545.spring.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class RefreshToken {
    @Id
    String token;
    @OneToOne
    User user;
    @OneToMany(mappedBy = "refreshToken", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<AccessToken> accessTokens;
}
