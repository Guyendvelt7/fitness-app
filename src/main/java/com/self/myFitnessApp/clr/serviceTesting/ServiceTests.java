package com.self.myFitnessApp.clr.serviceTesting;

import com.self.myFitnessApp.beans.Trainee;
import com.self.myFitnessApp.repo.TraineeRepo;
import com.self.myFitnessApp.service.TraineeService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(5)
@RequiredArgsConstructor
public class ServiceTests implements CommandLineRunner {
    private final TraineeService traineeService;
    private final TraineeRepo traineeRepo;
    @Override
    public void run(String... args) throws Exception {
        Trainee trainee = traineeRepo.findByEmail("coach1@gmail.com");
        //trainee service tests :
        traineeService.login("trainee1@gmail.com", "password1");
        traineeService.getTraineeDetails();
        traineeService.joinWorkout(1);
        traineeService.getAllMyWorkouts();
        traineeService.getRegisteredWorkout(1);
        traineeService.getWorkoutTrainees(1);
        traineeService.getCoachDetails(1);
        traineeService.login("trainee2@gmail.com", "password2");
        traineeService.joinWorkout(1);














    }
}
