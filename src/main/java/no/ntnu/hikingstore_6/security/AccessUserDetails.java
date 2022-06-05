package no.ntnu.hikingstore_6.security;

import no.ntnu.hikingstore_6.entities.Role;
import no.ntnu.hikingstore_6.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class AccessUserDetails implements UserDetails {

    private final String username;
    private final String email;
    private final String password;
    private final boolean enabled;
    private final List<GrantedAuthority> authorities = new LinkedList<>();

    public AccessUserDetails(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.enabled = user.isEnabled();
        this.convertRoles(user.getRoles());
    }




    private void convertRoles(Set<Role> roles) {
        authorities.clear();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
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
