package com.example.bookinar.entity.user;

import com.example.bookinar.entity.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "USER")
@Entity(name = "USER")
public class UserEntity implements UserDetails {


    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;

    private String password;

    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
    private Set<RoleEntity> roles;

    @Column(name = "DATE_REGISTER")
    private LocalDateTime dateRegister;

    @Column(name = "DATE_MODIFY")
    private LocalDateTime dateModify;

    @Column(name = "STATUS")
    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
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
