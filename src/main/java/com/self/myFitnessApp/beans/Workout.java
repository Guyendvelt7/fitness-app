package com.self.myFitnessApp.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "workouts")
@Builder
@ToString
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int coachId;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DayOfWeek day;
    @Column(nullable = false)
    private int startTime;
    private int endTime;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "workouts_vs_trainees", joinColumns = @JoinColumn(name = "workout_id"), inverseJoinColumns = @JoinColumn(name = "trainee_id"))
    @ToString.Exclude
    @JsonIgnore
    private Set<Trainee> trainees;

}
