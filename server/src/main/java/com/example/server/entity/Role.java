package com.example.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Role {
    @Id
    @GeneratedValue
    private Integer id;



    private String roleName;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Permission> permissions;



    public Role(String roleName, Set<Permission> permissions) {
        this.roleName = roleName;
        this.permissions=permissions;
    }




}
