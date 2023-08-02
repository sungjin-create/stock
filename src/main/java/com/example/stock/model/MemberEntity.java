package com.example.stock.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "MEMBER")
public class MemberEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    @ElementCollection
    private List<String> roles;

    public static MemberEntityBuilder builder() {
        return new MemberEntityBuilder();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public static class MemberEntityBuilder {
        private Long id;
        private String username;
        private String password;
        private List<String> roles;

        MemberEntityBuilder() {
        }

        public MemberEntityBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public MemberEntityBuilder username(String username) {
            this.username = username;
            return this;
        }

        public MemberEntityBuilder password(String password) {
            this.password = password;
            return this;
        }

        public MemberEntityBuilder roles(List<String> roles) {
            this.roles = roles;
            return this;
        }

        public MemberEntity build() {
            return new MemberEntity(this.id, this.username, this.password, this.roles);
        }

        public String toString() {
            return "MemberEntity.MemberEntityBuilder(id=" + this.id + ", username=" + this.username + ", password=" + this.password + ", roles=" + this.roles + ")";
        }
    }
}
