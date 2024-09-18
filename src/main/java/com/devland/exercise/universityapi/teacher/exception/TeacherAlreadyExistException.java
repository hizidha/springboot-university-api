package com.devland.exercise.universityapi.teacher.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class TeacherAlreadyExistException extends RuntimeException {
    public TeacherAlreadyExistException(String message) {
        super(message);
    }
}