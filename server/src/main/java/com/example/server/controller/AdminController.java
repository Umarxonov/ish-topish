package com.example.server.controller;

import com.example.server.entity.User;
import com.example.server.payload.ApiResponse;
import com.example.server.payload.UserDto;
import com.example.server.service.AdminService;
import com.example.server.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
   @Autowired
   AdminService adminService;

    @PostMapping("/save")
    public HttpEntity<ApiResponse> AdminAdd(@RequestBody UserDto userDto) {
        ApiResponse apiResponse =adminService.userSave (userDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? apiResponse.getMessage().equals("Saved") ? 201 : 202 : 409).body(apiResponse);

    }

@PostMapping("/edit/{id}")
    public HttpEntity<ApiResponse>edit(@PathVariable UUID id, @RequestBody UserDto userDto){

        ApiResponse apiResponse=adminService.edited(id,userDto);

    return ResponseEntity.status(apiResponse.isSuccess() ? apiResponse.getMessage().equals("Edited") ? 201 : 202 : 409).body(apiResponse);
}
@GetMapping("/all")

    public HttpEntity<List<User>> all(){
    return ResponseEntity.status(HttpStatus.OK).body(adminService.getAllUsers());
}

@DeleteMapping("/delete/{id}")
    public HttpEntity<ApiResponse> delete(@PathVariable UUID id){
        ApiResponse apiResponse=adminService.delete(id);
    return ResponseEntity.status(apiResponse.isSuccess() ? apiResponse.getMessage().equals("Delete") ? 201 : 202 : 409).body(apiResponse);
}


    @GetMapping("/allEmployeePageable")
    public HttpEntity<?> allByPageable(@RequestParam(value = "page",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER)Integer page,
                                       @RequestParam(value = "size",defaultValue = AppConstants.DEFAULT_PAGE_SIZE)Integer size,
                                       @RequestParam(value = "search",defaultValue = "all") String search
    ) throws IllegalAccessException {
        return ResponseEntity.ok(adminService.allByPageable(page,size,search));
    }

}
