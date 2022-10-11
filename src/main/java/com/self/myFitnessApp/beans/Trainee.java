package com.self.myFitnessApp.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "trainees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Trainee implements User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, length = 30)
    private String firstName;
    @Column(nullable = false, length = 30)
    private String lastName;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false, length = 18)
    private String password;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "trainees", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.JOIN)
    @JsonIgnore
    private Set<Coach> trainers;

    @ManyToMany( fetch = FetchType.EAGER, mappedBy = "trainees", cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.JOIN)
    @JsonIgnore
    Set<Workout> workouts;

    private void SetId(int id){
        this.id = id;
    }



}
