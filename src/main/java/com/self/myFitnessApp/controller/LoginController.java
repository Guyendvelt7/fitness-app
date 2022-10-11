package com.self.myFitnessApp.controller;

import com.self.myFitnessApp.beans.UserDetails;
import com.self.myFitnessApp.beans.UserLogin;
import com.self.myFitnessApp.exceptions.CustomExceptions;
import com.self.myFitnessApp.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/MyFitness")
public class LoginController {
 private final LoginService loginService;

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody UserLogin userLogin) throws CustomExceptions {
        String token = loginService.login(userLogin.getEmail(), userLogin.getPassword());
        return ResponseEntity.ok()
                .header("Authorization", token)
                .body(userLogin.getEmail() + " connected");
    }
}
