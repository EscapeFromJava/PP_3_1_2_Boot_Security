package ru.kata.spring.boot_security.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.Collection;

public class MyUserDetails implements UserDetails {

    private final User user;

    @Autowired
    public MyUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getLogin();
    }

    //не просрочен
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //не заблокирован
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //пароль не просрочен
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //активен
    @Override
    public boolean isEnabled() {
        return true;
    }
}
