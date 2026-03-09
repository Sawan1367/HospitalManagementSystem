package Main.entity;

import Main.entity.type.AuthProviderType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "hm_app_user")
public class User implements UserDetails {
    /**
     * What we want to do is that we want our DaoAuthenticationProvider to get the username and password from this User class only - that is why we implements UserDetails
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username",unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "provider id")
    private String providerId;

    @Enumerated(EnumType.STRING)
    @Column(name = "provider type")
    private AuthProviderType providerType;

    @Override
//    @NullMarked
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }
}
