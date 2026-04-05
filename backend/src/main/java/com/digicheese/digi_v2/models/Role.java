package com.digicheese.digi_v2.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 100)
    private String roleLabel;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();

    public Role() {
    }

    public Role(Integer id, String roleLabel) {
        this.id = id;
        this.roleLabel = roleLabel;
    }

    public Integer getId() {
        return id;
    }

    public String getRoleLabel() {
        return roleLabel;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setRoleLabel(String roleLabel) {
        this.roleLabel = roleLabel;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}