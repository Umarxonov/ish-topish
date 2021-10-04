package com.example.server.controller;

import com.example.server.entity.User;
import com.example.server.entity.enums.WorkEnum;
import com.example.server.payload.ApiResponse;
import com.example.server.payload.WorkDto;
import com.example.server.secret.CurrentUser;
import com.example.server.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/worker")
public class WorkerController {
    @Autowired
    WorkService workService;

    @PostMapping("/saveWorkByWorker")
    public ApiResponse saveWorkByWorker(@RequestBody WorkDto workDto, @CurrentUser User user) {
        workService.saveWorkByWorker(workDto, user);
        return new ApiResponse("Saved");
    }


    @PostMapping("/saveWorkByBoss")
    public ApiResponse saveWorkByBoss(@RequestBody WorkDto workDto, @CurrentUser User user) {
        workService.saveWorkByBoss(workDto, user);
        return new ApiResponse("Saved");
    }

    @GetMapping("/getMyOrder")
    public ApiResponse getMyOrder(@CurrentUser User user, @RequestBody WorkEnum workEnum) {
        workService.getMyOrder(user, workEnum);
        return null;
    }
}
