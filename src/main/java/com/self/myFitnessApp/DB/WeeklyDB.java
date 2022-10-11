package com.self.myFitnessApp.DB;

import com.self.myFitnessApp.beans.Coach;
import com.self.myFitnessApp.beans.Workout;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RequiredArgsConstructor
@Getter
@Setter
public class WeeklyDB {

    //register coach --> week -->
    Map<Coach,Workout[][]> myWeek = new HashMap<>();


public static Workout[][] week = new Workout[24][7];
public static Set<Coach> trainers = new HashSet<>();


}
