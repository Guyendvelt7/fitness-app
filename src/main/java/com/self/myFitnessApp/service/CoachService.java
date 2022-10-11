package com.self.myFitnessApp.service;

import com.self.myFitnessApp.DB.WeeklyDB;
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

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CoachService {
    private final CoachRepo coachRepo;
    private final WorkoutRepo workoutRepo;
    private final TraineeRepo traineeRepo;
    private int coachId;


    public void register(Coach coach) throws CustomExceptions {
        if (coachRepo.existsByEmail(coach.getEmail())) {
            throw new CustomExceptions(ExceptionsMessages.USER_ALREADY_EXISTS);
        }
        coachRepo.save(coach);
    }

    public boolean login(String email, String password) throws CustomExceptions {
        if (!coachRepo.existsByEmail(email)) {
            throw new CustomExceptions(ExceptionsMessages.USER_NOT_EXIST);
        }

        Coach coach = coachRepo.findByEmail(email);
        if (Objects.equals(coach.getPassword(), password)) {
            this.coachId = coach.getId();
            return true;
        }
        return false;
    }

    public void setWorkoutsRange(Workout workout, int endTime) throws CustomExceptions {
        if (!coachRepo.existsById(this.coachId)) {
            throw new CustomExceptions(ExceptionsMessages.DONT_HAVE_PERMISSIONS);
        }
        for (int i = workout.getStartTime(); i < endTime; i++) {
           List<Workout> workouts = new ArrayList<>();
            workouts.add(
                    Workout.builder()
                            .coachId(this.coachId)
                            .day(workout.getDay())
                            .startTime(i)
                            .endTime(i+1)
                            .trainees(workout.getTrainees())
                            .build()
            );
            workoutRepo.saveAll(workouts);
            //todo: get time of each workout
        }
    }

    public void setOneWorkout(Workout workout) throws CustomExceptions {
        if (!coachRepo.existsById(this.coachId)) {
            throw new CustomExceptions(ExceptionsMessages.DONT_HAVE_PERMISSIONS);
        }
        if(workout.getCoachId()!= this.coachId){
            throw new CustomExceptions(ExceptionsMessages.DONT_HAVE_PERMISSIONS);
        }

        //todo : conditions
        workoutRepo.save(workout);

    }

    public Set<Workout> getAllWorkouts() throws CustomExceptions {
        if (!coachRepo.existsById(this.coachId)) {
            throw new CustomExceptions(ExceptionsMessages.DONT_HAVE_PERMISSIONS);
        }
        return coachRepo.findCoachWorkouts(this.coachId);
    }

    public Workout getWorkout(int workoutId) throws CustomExceptions {
        if (!coachRepo.existsById(this.coachId)) {
            throw new CustomExceptions(ExceptionsMessages.DONT_HAVE_PERMISSIONS);
        }

        if(!workoutRepo.existsById(workoutId)){
            throw new CustomExceptions(ExceptionsMessages.WORKOUT_NOT_EXIST);
        }
        return workoutRepo.findByIdAndCoachId(workoutId, this.coachId);
    }

    public Coach getCoachDetails() throws CustomExceptions {

        if (!coachRepo.existsById(this.coachId)) {
            throw new CustomExceptions(ExceptionsMessages.DONT_HAVE_PERMISSIONS);
        }

        return coachRepo.findById(this.coachId).get();

    }

    public Set<Trainee> getAllTrainees() throws CustomExceptions {
        if (!coachRepo.existsById(this.coachId)) {
            throw new CustomExceptions(ExceptionsMessages.DONT_HAVE_PERMISSIONS);
        }

        return coachRepo.findAllCoachTrainees(this.coachId);
    }


    public Trainee getOneTrainee(int traineeId) throws CustomExceptions {
        if (!coachRepo.existsById(this.coachId)) {
            throw new CustomExceptions(ExceptionsMessages.DONT_HAVE_PERMISSIONS);
        }

        if(!traineeRepo.existsById(traineeId)){
            throw  new CustomExceptions(ExceptionsMessages.USER_NOT_EXIST);
        }

        return coachRepo.findOneCoachTrainee(traineeId, this.coachId);
    }


}
