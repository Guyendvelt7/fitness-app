package com.self.myFitnessApp.clr.repoTesting;

import com.self.myFitnessApp.beans.Workout;
import com.self.myFitnessApp.repo.CoachRepo;
import com.self.myFitnessApp.repo.TraineeRepo;
import com.self.myFitnessApp.repo.WorkoutRepo;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//@Component
@Order(4)
@RequiredArgsConstructor
public class RepoTests implements CommandLineRunner {
    private final CoachRepo coachRepo;
    private final WorkoutRepo workoutRepo;
    private final TraineeRepo traineeRepo;
    @Override
    public void run(String... args) throws Exception {
        //workout repo tests :
        System.out.println(workoutRepo.findByIdAndCoachId(1,1));
        System.out.println(workoutRepo.findAllWorkoutsByTraineeId(1));
        System.out.println(workoutRepo.findByIdAndTraineeId(2,1));

        //trainee repo tests
       System.out.println(traineeRepo.findWorkoutTrainees(2));

        //coach repo tests
        System.out.println(coachRepo.findCoachWorkouts(1));
        System.out.println(coachRepo.findAllCoachTrainees(1));


    }
}
