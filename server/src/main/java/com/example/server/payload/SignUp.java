package com.example.server.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUp {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String password;
}
