package com.self.myFitnessApp.clr.dbBuild;

import com.self.myFitnessApp.beans.Coach;
import com.self.myFitnessApp.beans.DayOfWeek;
import com.self.myFitnessApp.beans.Trainee;
import com.self.myFitnessApp.beans.Workout;
import com.self.myFitnessApp.repo.CoachRepo;
import com.self.myFitnessApp.repo.TraineeRepo;
import com.self.myFitnessApp.repo.WorkoutRepo;
import com.self.myFitnessApp.service.TraineeService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@Order(3)
@RequiredArgsConstructor
public class DB_Workout implements CommandLineRunner {
    private final TraineeRepo traineeRepo;
    private final CoachRepo coachRepo;
    private final WorkoutRepo workoutRepo;

    @Override
    public void run(String... args) throws Exception {

        Workout workout1 = Workout.builder()
                .id(1)
                .coachId(1)
                .day(DayOfWeek.MONDAY)
                .startTime(9)
                .endTime(10)
                .build();

        Workout workout2 = Workout.builder()
                .id(2)
                .coachId(coachRepo.findByEmail("coach2@gmail.com").getId())
                .day(DayOfWeek.MONDAY)
                .startTime(10)
                .endTime(11)
                .build();


        workoutRepo.saveAll(List.of(workout1,workout2));
    }
}
