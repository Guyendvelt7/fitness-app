package com.self.myFitnessApp.exceptions;

public class CustomExceptions extends Exception{

    public CustomExceptions(ExceptionsMessages message){
        super(message.getMessage());
    }

}
