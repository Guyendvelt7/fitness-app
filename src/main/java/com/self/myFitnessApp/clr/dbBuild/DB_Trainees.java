package com.self.myFitnessApp.clr.dbBuild;

import com.self.myFitnessApp.beans.Trainee;
import com.self.myFitnessApp.repo.TraineeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Order(1)
@RequiredArgsConstructor
public class DB_Trainees implements CommandLineRunner {
    private final TraineeRepo traineeRepo;
    @Override
    public void run(String... args) throws Exception {

        Trainee trainee1 = Trainee.builder()
                .firstName("trainee1")
                .lastName("last1")
                .email("trainee1@gmail.com")
                .id(1)
                .password("password1")
                .build();
        Trainee trainee2 = Trainee.builder()
                .firstName("trainee2")
                .lastName("last2")
                .email("trainee2@gmail.com")
                .id(2)
                .password("password2")
                .build();
        Trainee trainee3 = Trainee.builder()
                .firstName("trainee3")
                .lastName("last3")
                .email("trainee3@gmail.com")
                .id(3)
                .password("password3")
                .build();
        Trainee trainee4 = Trainee.builder()
                .firstName("trainee4")
                .lastName("last4")
                .email("trainee4@gmail.com")
                .id(4)
                .password("password4")
                .build();
        Trainee trainee5 = Trainee.builder()
                .firstName("trainee5")
                .lastName("last5")
                .email("trainee5@gmail.com")
                .id(5)
                .password("password5")
                .build();

        traineeRepo.saveAll(List.of(trainee1, trainee2, trainee3, trainee4, trainee5));
    }


}
