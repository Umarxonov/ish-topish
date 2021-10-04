package com.example.server.service;

import com.example.server.entity.User;
import com.example.server.payload.ApiResponse;
import com.example.server.payload.UserDto;
import com.example.server.repository.RoleRepository;
import com.example.server.repository.UserRepository;
import com.example.server.secret.JwtProvider;
import com.example.server.utils.CommonUtills;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class AdminService {


    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    AuthenticationManager manager;

    @Autowired
    JwtProvider jwtProvider;


    public ApiResponse userSave(UserDto userDto) {
 try {
     User user = new User();
     if (userDto.getId()!=null){
        user=userRepository.getById(userDto.getId());
     }

     user.setFirstName(userDto.getFirstName());
     user.setLastName(userDto.getLastName());
     user.setPassword(passwordEncoder.encode(userDto.getPassword()));
//        user.setUsername(userDto.getUsername());

     user.setPhoneNumber(userDto.getPhoneNumber());


     user.setRoles(Arrays.asList(roleRepository.findByRoleName(userDto.getRole())));
     user.setActive(userDto.isActive());
     userRepository.save(user);
     return new ApiResponse("Saved", true);

 }catch (Exception e){
     e.printStackTrace();
 }

        return new ApiResponse("Error", false);
    }


    public ApiResponse edited(UUID id, UserDto userDto) {

        User user = userRepository.getById(id);
        user.setFirstName(userDto.getFirstName() == null ? user.getLastName() : userDto.getFirstName());
        user.setLastName(userDto.getLastName() == null ? user.getLastName() : userDto.getLastName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword() == null ? user.getPassword() : userDto.getPassword()));
//        user.setUsername(userDto.getUsername() == null ? user.getUsername() : userDto.getUsername());

        user.setPhoneNumber(userDto.getPhoneNumber() == null ? user.getPhoneNumber() : userDto.getPhoneNumber());
        user.setRoles(userDto.getRole() == null ? user.getRoles() : Arrays.asList(roleRepository.findByRoleName(userDto.getRole())));
        user.setActive(userDto.isActive());
        userRepository.save(user);
        return new ApiResponse("Edited", true);
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();

    }


    public ApiResponse delete(UUID id) {
        userRepository.deleteById(id);

        return new ApiResponse("Delete", true);

    }


    public ApiResponse allByPageable(Integer page, Integer size, String search) throws IllegalAccessException {
        Page<User> userPage = userRepository.findAll(CommonUtills.getPageableByIdDesc(page, size));

        return new ApiResponse(true, "User Page", userPage.getContent(), userPage.getTotalElements());
    }
}
