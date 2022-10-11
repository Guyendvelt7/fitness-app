package com.self.myFitnessApp.repo;

import com.self.myFitnessApp.beans.Coach;
import com.self.myFitnessApp.beans.Trainee;
import com.self.myFitnessApp.beans.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface CoachRepo extends JpaRepository<Coach,Integer> {
    boolean existsByEmail(String email);
    Coach findByEmail(String email);


    @Query(value = "select workout from Workout workout where coachId = ?1")
    Set<Workout> findCoachWorkouts(int coachId);
    @Query("SELECT trainee FROM Trainee trainee join trainee.trainers where trainer_id =?1")
    Set<Trainee> findAllCoachTrainees(int coachId);

    @Query("SELECT trainee FROM Trainee trainee join trainee.trainers where trainer_id = ?1 and trainee_id = ?2")
    Trainee findOneCoachTrainee(int coachId, int traineeId);








/*
@
    Set<Trainee> findAllCoachTrainees(int coachId);


    @Query("SELECT trainee FROM Trainee trainee WHERE trainee.id = ?1 " +
            "(SELECT trainee.id FROM trainee.trainers trainer WHERE trainer.id = ?2)")
    Trainee findSpecificCoachTrainee(int traineeId, int coachId);


     */

}
