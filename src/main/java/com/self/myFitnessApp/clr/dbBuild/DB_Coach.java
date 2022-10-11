package com.self.myFitnessApp.clr.dbBuild;

import com.self.myFitnessApp.beans.Coach;
import com.self.myFitnessApp.repo.CoachRepo;
import com.self.myFitnessApp.repo.TraineeRepo;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Order(2)
public class DB_Coach implements CommandLineRunner {
    private final CoachRepo coachRepo;
    private final TraineeRepo traineeRepo;
    @Override
    public void run(String... args) throws Exception {
        Coach coach1 = Coach.builder()
                .id(1)
                .firstName("coach1")
                .lastName("last1")
                .email("coach1@gmail.com")
                .password("password1")
                .trainees(Set.of(traineeRepo.findById(1).get(), traineeRepo.findById(2).get()))
                .build();

        Coach coach2 = Coach.builder()
                .id(2)
                .firstName("coach2")
                .lastName("last2")
                .email("coach2@gmail.com")
                .password("password2")
                .trainees(Set.of(traineeRepo.findById(3).get(), traineeRepo.findById(4).get()))
                .build();

        coachRepo.saveAll(List.of(coach1, coach2));
    }
}
