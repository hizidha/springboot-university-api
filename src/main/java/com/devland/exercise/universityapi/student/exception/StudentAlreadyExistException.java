package com.devland.exercise.universityapi.student.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class StudentAlreadyExistException extends RuntimeException {
    public StudentAlreadyExistException(String message) {
        super(message);
    }
}
