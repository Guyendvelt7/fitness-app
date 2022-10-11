package com.self.myFitnessApp.service;

import com.self.myFitnessApp.beans.ClientType;
import com.self.myFitnessApp.beans.LoginCheck;
import com.self.myFitnessApp.beans.UserDetails;
import com.self.myFitnessApp.exceptions.CustomExceptions;
import com.self.myFitnessApp.exceptions.ExceptionsMessages;
import com.self.myFitnessApp.repo.CoachRepo;
import com.self.myFitnessApp.repo.TraineeRepo;
import com.self.myFitnessApp.security.JWTutil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final TraineeRepo traineeRepo;
    private final CoachRepo coachRepo;
    private final TraineeService traineeService;
    private final CoachService coachService;
    private final JWTutil jwTutil;

    public String login(String email, String password) throws CustomExceptions {
        LoginCheck loginCheck = LoginCheck.builder()
                .isLogin(false)
                .clientType(ClientType.TRAINEE)
                .build();
        if(!traineeRepo.existsByEmail(email) && !coachRepo.existsByEmail(email)){
            throw new CustomExceptions(ExceptionsMessages.USER_NOT_EXIST);
        }
        else if(traineeRepo.existsByEmail(email)){
          loginCheck.setLogin(traineeService.login(email, password));
        }
        else {
           loginCheck.setLogin(coachService.login(email, password));
           loginCheck.setClientType(ClientType.COACH);
        }

        if(!loginCheck.isLogin()){
            throw new CustomExceptions(ExceptionsMessages.LOGIN_FAILED);
        }
        UserDetails userDetails = new UserDetails(email, password, loginCheck.getClientType());
        return jwTutil.generateToken(userDetails);
    }




}
