package med.voll.api.domain.entities;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "tb_users")
@Entity(name = "UsersEntity")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class UsersEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String password;
   
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        
    }
    @Override
    public String getUsername() {
        return login;
        
    }

    @Override
    public String getPassword(){
        return password;

    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
        
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
        
    }
    @Override
    public boolean isEnabled() {
        return true;
        
    }
    
}
