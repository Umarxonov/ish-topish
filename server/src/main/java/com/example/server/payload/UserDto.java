package com.example.server.payload;

import com.example.server.entity.Permission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private UUID id;
    private String firstName;
    private String lastName;
//    private String username;
    private String password;


    private String phoneNumber;

    private boolean active;
    private Set<Permission> permissions;
    private String role;


}
