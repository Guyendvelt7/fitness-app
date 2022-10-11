package com.self.myFitnessApp.service;

import com.self.myFitnessApp.beans.Coach;
import com.self.myFitnessApp.beans.Trainee;
import com.self.myFitnessApp.beans.Workout;
import com.self.myFitnessApp.exceptions.CustomExceptions;
import com.self.myFitnessApp.exceptions.ExceptionsMessages;
import com.self.myFitnessApp.repo.CoachRepo;
import com.self.myFitnessApp.repo.TraineeRepo;
import com.self.myFitnessApp.repo.WorkoutRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TraineeService {
private final TraineeRepo traineeRepo;
private final WorkoutRepo workoutRepo;
private final CoachRepo coachRepo;
private int traineeId;

    public void register(Trainee trainee) throws CustomExceptions {
        if(traineeRepo.existsByEmail(trainee.getEmail())){
            throw new CustomExceptions(ExceptionsMessages.USER_ALREADY_EXISTS);
        }
        traineeRepo.save(trainee);
    }

    public boolean login(String email, String password) throws CustomExceptions {
        if(!traineeRepo.existsByEmail(email)){
            throw new CustomExceptions(ExceptionsMessages.USER_NOT_EXIST);
        }

        Trainee trainee = traineeRepo.findByEmail(email);
        if(Objects.equals(trainee.getPassword(),password)){
            this.traineeId = trainee.getId();
            return true;
        }

            return false;
    }

    public Set<Workout> getAllMyWorkouts() throws CustomExceptions {
        if(!traineeRepo.existsById(this.traineeId)){
            throw new CustomExceptions(ExceptionsMessages.USER_NOT_EXIST);
        }
        return workoutRepo.findAllWorkoutsByTraineeId(this.traineeId);
    }

    public Workout getRegisteredWorkout(int workoutId) throws CustomExceptions {
        if(!traineeRepo.existsById(this.traineeId)){
            throw new CustomExceptions(ExceptionsMessages.USER_NOT_EXIST);
        }
        if(!workoutRepo.existsById(workoutId)){
            throw new CustomExceptions(ExceptionsMessages.WORKOUT_NOT_EXIST);
        }

      if(Objects.equals(workoutRepo.findByIdAndTraineeId(workoutId, this.traineeId),null)){
          throw new CustomExceptions(ExceptionsMessages.WORKOUT_NOT_ALLOWED);
      }

        return workoutRepo.findByIdAndTraineeId(workoutId, this.traineeId);
    }

    public Coach getCoachDetails(int coachId) throws CustomExceptions {
        if(!traineeRepo.existsById(this.traineeId)){
            throw new CustomExceptions(ExceptionsMessages.USER_NOT_EXIST);
        }
        if(!coachRepo.existsById(coachId)){
            throw new CustomExceptions(ExceptionsMessages.COACH_NOT_EXIST);
        }
        return coachRepo.findById(coachId).get();
    }


    public Trainee getTraineeDetails() throws CustomExceptions {
        if(!traineeRepo.existsById(this.traineeId)){
            throw new CustomExceptions(ExceptionsMessages.USER_NOT_EXIST);
        }
        return traineeRepo.findById(traineeId).get();

    }

    public Set<Trainee> getWorkoutTrainees(int workoutId) throws CustomExceptions {
        if(!traineeRepo.existsById(this.traineeId)){
            throw new CustomExceptions(ExceptionsMessages.USER_NOT_EXIST);
        }
        if(!workoutRepo.existsById(workoutId)){
            throw new CustomExceptions(ExceptionsMessages.WORKOUT_NOT_EXIST);
        }
        return traineeRepo.findWorkoutTrainees(workoutId);
    }

    public void joinWorkout(int workoutId) throws CustomExceptions {
        if(!traineeRepo.existsById(this.traineeId)){
            throw new CustomExceptions(ExceptionsMessages.USER_NOT_EXIST);
        }
        if(!workoutRepo.existsById(workoutId)){
            throw new CustomExceptions(ExceptionsMessages.WORKOUT_NOT_EXIST);
        }
        if(workoutRepo.isTraineeAlreadyInWorkout(this.traineeId, workoutId) > 0){
            throw new CustomExceptions(ExceptionsMessages.WORKOUT_ALREADY_REGISTERED);
        }


        if(workoutRepo.findAllWorkoutsByTraineeId(this.traineeId).size() > 5){
            throw new CustomExceptions(ExceptionsMessages.LIMIT_OF_WORKOUTS);
        } else {
                traineeRepo.joinWorkout(workoutId,this.traineeId);
        }







    }



}
