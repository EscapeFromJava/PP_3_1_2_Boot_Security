package ru.kata.spring.boot_security.demo.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.JdbcUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.util.UserGenerator;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final SuccessUserHandler successUserHandler;
    private final UserService userService;
    private final DataSource dataSource;

    public WebSecurityConfig(SuccessUserHandler successUserHandler, UserService userService, DataSource dataSource) {
        this.successUserHandler = successUserHandler;
        this.userService = userService;
        this.dataSource = dataSource;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/user").hasAnyRole("ADMIN", "USER")
                .antMatchers("/admin").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin().successHandler(successUserHandler).permitAll()
                .and()
                .logout().logoutSuccessUrl("/login").permitAll()
        ;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
//        UserGenerator.createUsersWithRoles().forEach(userService::saveUser);
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userService);
        return authenticationProvider;
    }

//    @PostConstruct
//    public void addUsersWithRoles() {
//        UserGenerator.createUsersWithRoles().forEach(userService::saveUser);
//    }


//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        JdbcUserDetailsManagerConfigurer<AuthenticationManagerBuilder> builder = auth.jdbcAuthentication();
//        builder.dataSource(dataSource);
//        JdbcUserDetailsManager userDetailsManager = builder.getUserDetailsService();
//        userDetailsManager.setUsersByUsernameQuery("SELECT login, password, enabled FROM my_db.users WHERE login = ?");
//        userDetailsManager.setAuthoritiesByUsernameQuery("SELECT login, role FROM my_db.users u LEFT JOIN my_db.user_role ur ON u.id = ur.user_id LEFT JOIN my_db.roles r ON r.id = ur.role_id WHERE login = ?");
//        userDetailsManager.setCreateUserSql("INSERT INTO my_db.users (login, password, enabled) VALUES (?,?,?)");
//        userDetailsManager.setCreateAuthoritySql("INSERT INTO my_db.user_role (user_id,role_id) VALUES ((SELECT id FROM my_db.users WHERE login = ?),(SELECT id FROM my_db.roles WHERE role = ?))");
//        builder.withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN");
//        builder.withUser("user1").password(passwordEncoder().encode("user1")).roles("USER");
//        builder.withUser("user2").password(passwordEncoder().encode("user2")).roles("USER");
//        builder.withUser("adminuser").password(passwordEncoder().encode("adminuser")).roles("ADMIN", "USER");
//    }
}