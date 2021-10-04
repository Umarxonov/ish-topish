package com.example.server.service;

import com.example.server.entity.User;
import com.example.server.entity.enums.RoleName;
import com.example.server.payload.ApiResponse;
import com.example.server.payload.SignIn;
import com.example.server.payload.SignUp;
import com.example.server.repository.RoleRepository;
import com.example.server.repository.UserRepository;
import com.example.server.secret.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager manager;

    @Autowired
    JwtProvider jwtProvider;

    public ApiResponse register(SignUp signUp) {
        Optional<User> userOptional = userRepository.findByPhoneNumber(signUp.getPhoneNumber());
        if (!userOptional.isPresent()) {
            User user = new User();
            user.setPhoneNumber(signUp.getPhoneNumber());
            user.setPassword(passwordEncoder.encode(signUp.getPassword()));
            user.setFirstName(signUp.getFirstName());
            user.setLastName(signUp.getLastName());
            user.setRoles(roleRepository.findAllByRoleNameIn(Collections.singleton(RoleName.ROLE_CLIENT.name())));

            userRepository.save(user);
            return new ApiResponse("Saved", true, jwtProvider.generateJwtToken(user));
        } else {
            return new ApiResponse("user is already :))", true);
        }
    }


    public ApiResponse edit(User user, SignUp signUp) {
        user.setPhoneNumber(signUp.getPhoneNumber() == null ? user.getPhoneNumber() : signUp.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(signUp.getPassword()));
        user.setFirstName(signUp.getFirstName());
        user.setLastName(signUp.getLastName());
        user.setRoles(roleRepository.findAllByRoleNameIn(Collections.singleton(RoleName.ROLE_CLIENT.name())));
        userRepository.save(user);
        return new ApiResponse("Edit", true);


    }


    public String login(SignIn signIn) {
        Authentication authenticate = manager.authenticate(
                new UsernamePasswordAuthenticationToken(signIn.getPhoneNumber(), signIn.getPassword())

        );
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        User principal = (User) authenticate.getPrincipal();
        String token = jwtProvider.generateJwtToken(principal);
        return token;


    }


    public ApiResponse delete(UUID id) {
        userRepository.deleteById(id);
        return new ApiResponse("Delete", true);

    }


}
