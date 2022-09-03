package ru.kata.spring.boot_security.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String role;

    @ManyToMany
    @JoinTable(
            name = "user_role"
            , joinColumns = @JoinColumn(name = "role_id")
            , inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;

    public Role(String role) {
        this.role = role;
    }

    public void addUserToRoles(User user) {
        if (users == null) {
            users = new ArrayList<>();
        }
        users.add(user);
    }


}
