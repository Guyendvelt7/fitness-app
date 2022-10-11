package com.self.myFitnessApp.controller;

import com.self.myFitnessApp.beans.ClientType;
import com.self.myFitnessApp.beans.Trainee;
import com.self.myFitnessApp.exceptions.CustomExceptions;
import com.self.myFitnessApp.security.JWTutil;
import com.self.myFitnessApp.service.TraineeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/trainee")
public class TraineeController {
    private final TraineeService traineeService;
    private final JWTutil jwTutil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Trainee trainee) throws CustomExceptions {
        traineeService.register(trainee);
        return ResponseEntity.ok()
                .body(trainee.getFirstName() + " registered");
    }

    @GetMapping("/getAlMyWorkouts")
    public ResponseEntity<?> getAllMyWorkouts(@RequestHeader(name = "Authorization") String token) throws CustomExceptions {
        String newToken = jwTutil.checkUser(token, ClientType.TRAINEE);
        return ResponseEntity.ok()
                .header("Authorization", newToken)
                .body(traineeService.getAllMyWorkouts());
    }


    @GetMapping("/getRegisteredWorkout/{workoutId}")
    public ResponseEntity<?> getRegisteredWorkout(@RequestHeader(name = "Authorization") String token, @PathVariable int workoutId) throws CustomExceptions {
        String newToken = jwTutil.checkUser(token, ClientType.TRAINEE);
        return ResponseEntity.ok()
                .header("Authorization", newToken)
                .body(traineeService.getRegisteredWorkout(workoutId));
    }

    @GetMapping("/getCoachDetails/{coachId}")
    public ResponseEntity<?> getCoachDetails(@RequestHeader(name = "Authorization") String token, @PathVariable int coachId) throws CustomExceptions {
        String newToken = jwTutil.checkUser(token, ClientType.TRAINEE);
        return ResponseEntity.ok()
                .header("Authorization", newToken)
                .body(traineeService.getCoachDetails(coachId));
    }

    @GetMapping("/getTraineeDetails")
    public ResponseEntity<?> getTraineeDetails(@RequestHeader(name = "Authorization") String token) throws CustomExceptions {
        String newToken = jwTutil.checkUser(token, ClientType.TRAINEE);
        return ResponseEntity.ok()
                .header("Authorization", newToken)
                .body(traineeService.getTraineeDetails());
    }

    @GetMapping("/getWorkoutTrainees/{workoutId}")
    public ResponseEntity<?> getWorkoutTrainees(@RequestHeader(name = "Authorization") String token, @PathVariable int workoutId) throws CustomExceptions {
        String newToken = jwTutil.checkUser(token, ClientType.TRAINEE);
        return ResponseEntity.ok()
                .header("Authorization", newToken)
                .body(traineeService.getWorkoutTrainees(workoutId));
    }
    @PostMapping("/joinWorkout/{workoutId}")
    public ResponseEntity<?> joinWorkout(@RequestHeader(name = "Authorization") String token, @PathVariable int workoutId) throws CustomExceptions {
        String newToken = jwTutil.checkUser(token, ClientType.TRAINEE);
        traineeService.joinWorkout(workoutId);
        return ResponseEntity.ok()
                .header("Authorization", newToken)
                .body(traineeService.getRegisteredWorkout(workoutId));
    }
}
