package com.self.myFitnessApp.controller;


import com.self.myFitnessApp.beans.*;
import com.self.myFitnessApp.exceptions.CustomExceptions;
import com.self.myFitnessApp.repo.CoachRepo;
import com.self.myFitnessApp.security.JWTutil;
import com.self.myFitnessApp.service.CoachService;
import com.self.myFitnessApp.service.GuestService;
import com.self.myFitnessApp.service.TraineeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/guest")
public class GuestController {
    private final GuestService guestService;
    private final JWTutil jwTutil;


    /*
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user){
        guestService.register(user);
        return ResponseEntity.ok()
                .body(user);
    }

     */
}
