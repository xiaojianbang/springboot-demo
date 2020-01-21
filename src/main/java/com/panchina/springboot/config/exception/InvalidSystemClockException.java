package com.panchina.springboot.config.exception;

public class InvalidSystemClockException extends Exception {
    public InvalidSystemClockException(String message){
        super(message);
    }
}
