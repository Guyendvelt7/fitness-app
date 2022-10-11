package com.self.myFitnessApp.exceptions;

public enum ExceptionsMessages {
    USER_NOT_EXIST("User Not Exists"),
    COACH_NOT_EXIST("Coach Not Exists"),
    WORKOUT_NOT_EXIST("Workout Not Exists"),
    WORKOUT_ALREADY_REGISTERED("you already in this workout!"),
    LIMIT_OF_WORKOUTS("You have already arrived to your workout limit"),
    LOGIN_FAILED("login failed, wrong email or password"),
    DONT_HAVE_PERMISSIONS("you dont have permissions!"),
    WORKOUT_NOT_ALLOWED("you don't have this workout!"),
    USER_ALREADY_EXISTS("user already exists!");



    private String message;

    private ExceptionsMessages(String message){
        setMessage(message);
    }
    public String getMessage(){
        return this.message;
    }

    public void setMessage(String message){
        this.message = message;
    }

}
