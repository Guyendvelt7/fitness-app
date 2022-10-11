package com.self.myFitnessApp.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "trainers")
@Builder
@ToString
public class Coach implements User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, length = 30)
    private String firstName;
    @Column(nullable = false, length = 30)
    private String lastName;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false, length = 20)
    private String password;
    @Enumerated(EnumType.STRING)
    private WorkoutTypes type;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "coachId", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.JOIN)
    @JsonIgnore
    private Set<Workout> workouts;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "trainers_vs_trainees", joinColumns = @JoinColumn(name = "trainer_id"), inverseJoinColumns = @JoinColumn(name = "trainee_id"))
    @ToString.Exclude
    @JsonIgnore
    private Set<Trainee> trainees;



}
