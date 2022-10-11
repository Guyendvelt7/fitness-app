package com.self.myFitnessApp.controller;

import com.self.myFitnessApp.beans.ClientType;
import com.self.myFitnessApp.beans.Coach;
import com.self.myFitnessApp.beans.Workout;
import com.self.myFitnessApp.exceptions.CustomExceptions;
import com.self.myFitnessApp.security.JWTutil;
import com.self.myFitnessApp.service.CoachService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coach")
public class CoachController {
    private final CoachService coachService;
    private final JWTutil jwTutil;


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Coach coach) throws CustomExceptions {
        coachService.register(coach);
        return ResponseEntity.ok()
                .body(coach);
    }

    @PostMapping("/setWorkoutRange/{endTime}")
    public ResponseEntity<?> setWorkoutRange(@RequestHeader(name = "Authorization") String token, @RequestBody Workout workout, @PathVariable int endTime) throws CustomExceptions {
        String newToken = jwTutil.checkUser(token, ClientType.COACH);
        coachService.setWorkoutsRange(workout, endTime);
        return ResponseEntity.ok()
                        .header("Authorization", newToken)
                                .body(coachService.getAllWorkouts());

    }

    @PostMapping("/setWorkout")
    public ResponseEntity<?> setOneWorkout(@RequestHeader(name = "Authorization") String token, @RequestBody Workout workout) throws CustomExceptions {
        String newToken = jwTutil.checkUser(token, ClientType.COACH);
        coachService.setOneWorkout(workout);
        return  ResponseEntity.ok()
                .header("Authorization", newToken)
                .body(coachService.getAllWorkouts());
    }

    @GetMapping("/getAllWorkouts")
    public ResponseEntity<?> getAllWorkouts(@RequestHeader(name = "Authorization") String token) throws CustomExceptions {
        String newToken = jwTutil.checkUser(token, ClientType.COACH);
        return ResponseEntity.ok()
                .header("Authorization", newToken)
                .body(coachService.getAllWorkouts());
    }

    @GetMapping("/getWorkout/{workoutId}")
    public ResponseEntity<?> getWorkout(@RequestHeader(name = "Authorization") String token, @PathVariable int workoutId) throws CustomExceptions {
        String newToken = jwTutil.checkUser(token, ClientType.COACH);
        return ResponseEntity.ok()
                .header("Authorization", newToken)
                .body(coachService.getWorkout(workoutId));
    }

    @GetMapping("/getDetails")
    public ResponseEntity<?> getCoachDetails(@RequestHeader(name = "Authorization") String token) throws CustomExceptions {
        String newToken = jwTutil.checkUser(token, ClientType.COACH);
        return ResponseEntity.ok()
                .header("Authorization", newToken)
                .body(coachService.getCoachDetails());
    }

    @GetMapping("/getAllTrainees")
    public ResponseEntity<?> getAllTrainees(@RequestHeader(name = "Authorization") String token) throws CustomExceptions {
        String newToken = jwTutil.checkUser(token, ClientType.COACH);
        return ResponseEntity.ok()
                .header("Authorization", newToken)
                .body(coachService.getAllTrainees());
    }


/*
    @GetMapping("/getTrainee/{traineeId}")
    public ResponseEntity<?> getTrainee(@PathVariable int traineeId) throws CustomExceptions {
        return ResponseEntity.ok()
                .body(coachService.getOneTrainee(traineeId));

    }


 */

}
