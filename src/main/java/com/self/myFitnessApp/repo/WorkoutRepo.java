package com.self.myFitnessApp.repo;

import com.self.myFitnessApp.beans.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface WorkoutRepo extends JpaRepository<Workout,Integer> {

    public Workout findByIdAndCoachId(int id, int coachId);

    @Query(value = "select workout from Workout workout join workout.trainees where trainee_id = ?1")
    public Set<Workout> findAllWorkoutsByTraineeId(int traineeId);

    @Query("select workout from Workout workout join workout.trainees where workout.id = ?1 and trainee_id = ?2")
   public Workout findByIdAndTraineeId(int workoutId, int traineeId);
    @Query(value = "SELECT COUNT(*) FROM workouts_vs_trainees WHERE trainee_id =? and workout_id =?", nativeQuery = true)
    public int isTraineeAlreadyInWorkout(int traineeId, int workoutId);



}
