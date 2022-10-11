package com.self.myFitnessApp.service;

import com.self.myFitnessApp.beans.*;
import com.self.myFitnessApp.exceptions.CustomExceptions;
import com.self.myFitnessApp.exceptions.ExceptionsMessages;
import com.self.myFitnessApp.repo.CoachRepo;
import com.self.myFitnessApp.repo.TraineeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class GuestService {
    private final CoachRepo coachRepo;
    private final TraineeRepo traineeRepo;
    private final CoachService coachService;
    private final TraineeService traineeService;



    public ClientType login(UserLogin userLogin) throws CustomExceptions {

        if(!coachRepo.existsByEmail(userLogin.getEmail()) && !traineeRepo.existsByEmail(userLogin.getEmail())){
            throw new CustomExceptions(ExceptionsMessages.USER_NOT_EXIST);
        }

        if(coachRepo.existsByEmail(userLogin.getEmail())){
            coachService.login(userLogin.getEmail(), userLogin.getPassword());
            return ClientType.COACH;
        }
        else {
            traineeService.login(userLogin.getEmail(), userLogin.getPassword());
            return ClientType.TRAINEE;
        }

    }
/*
    public void register(User user){
        if(user instanceof Coach){
           coachRepo.save((Coach)user);
        }
        else {
            traineeRepo.save((Trainee)user);
        }
    }

 */


}
