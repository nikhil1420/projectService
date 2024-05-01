package dev.nikhil.projectservice.controllers;

import dev.nikhil.projectservice.dtos.ErrorResponseDto;
import dev.nikhil.projectservice.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvices {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDto> notFoundExceptionHandling(Exception exception) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setErrorMessage(exception.getMessage());

        return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
       // return new ResponseEntity<>("Phat gaya", HttpStatus.OK);
    }
}
