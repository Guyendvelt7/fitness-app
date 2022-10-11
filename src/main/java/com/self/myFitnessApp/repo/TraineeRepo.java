package com.self.myFitnessApp.repo;

import com.self.myFitnessApp.beans.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Set;

public interface TraineeRepo extends JpaRepository<Trainee,Integer> {
    public boolean existsByEmail(String email);
    public Trainee findByEmail(String email);
    @Query("SELECT trainee FROM Trainee trainee WHERE trainee.id= any " +
            "(SELECT trainee.id FROM trainee.workouts workout WHERE workout.id = ?1)")
    public Set<Trainee> findWorkoutTrainees(int workoutId);
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO `workouts_vs_trainees` (workout_id, trainee_id) VALUES (?,?)", nativeQuery = true)
    public void joinWorkout(int workoutId, int traineeId);


}
