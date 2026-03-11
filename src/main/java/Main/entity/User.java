package Main.entity;

import Main.entity.type.AuthProviderType;
import Main.entity.type.RoleType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.management.relation.Role;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    @ElementCollection(fetch = FetchType.EAGER)
    /**
     * @ElementCollection annotation is used to map a collection of simple values or embeddable objects that do not have their own entity identity.
     * @ElementCollection will form a new table to store roles and will return all the roles, whenever we call User because the FetchType is Eager
     * It is used when you want to store a list/set of non-entity values related to an entity.
     */
    @Enumerated(EnumType.STRING)
    Set<RoleType> roles = new HashSet<>();

    @Override
//    @NullMarked
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_"+role.name()))
                .collect(Collectors.toSet());
    }
}
