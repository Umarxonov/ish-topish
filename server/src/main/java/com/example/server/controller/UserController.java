package com.example.server.controller;

import com.example.server.entity.User;
import com.example.server.payload.ApiResponse;
import com.example.server.payload.SignIn;
import com.example.server.payload.SignUp;
import com.example.server.secret.CurrentUser;
import com.example.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public HttpEntity<ApiResponse> userAdd(@RequestBody SignUp userDto) {
        ApiResponse apiResponse = userService.register(userDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? apiResponse.getMessage().equals("Saved") ? 201 : 202 : 409).body(apiResponse);

    }

    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody SignIn signIn) {
        return ResponseEntity.ok(userService.login(signIn));
    }

    @PostMapping("/edit/{id}")
    public HttpEntity<ApiResponse> edit(@CurrentUser User user, @RequestBody SignUp userDto) {
        ApiResponse apiResponse = userService.edit(user,userDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? apiResponse.getMessage().equals("Edit") ? 201 : 202 : 409).body(apiResponse);

    }

    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> delete(@PathVariable UUID id){
        ApiResponse apiResponse=userService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? apiResponse.getMessage().equals("Delete") ? 201 : 202 : 409).body(apiResponse);
    }


    @GetMapping("/me")
    public HttpEntity<?> me(@CurrentUser User user){
        return ResponseEntity.status(user!=null?200:409).body(user);
    }


}
