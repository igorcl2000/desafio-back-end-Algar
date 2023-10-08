package algar.desafio.api.exception;

import java.time.LocalDateTime;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import algar.desafio.api.dto.ExceptionDTO;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class Exceptions {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleException(ResourceNotFoundException exception, HttpServletRequest request){
        ExceptionDTO exceptionDTO = new ExceptionDTO(
            exception.getMessage(),
            HttpStatus.NOT_FOUND.value()
        );

        return new ResponseEntity<>(exceptionDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleException(RuntimeException exception, HttpServletRequest request){
        ExceptionDTO exceptionDTO = new ExceptionDTO(
            exception.getMessage(),
            HttpStatus.BAD_REQUEST.value()
        );

        return new ResponseEntity<>(exceptionDTO, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleException(DataIntegrityViolationException exception, HttpServletRequest request){
        ExceptionDTO exceptionDTO = new ExceptionDTO(
            exception.getMessage(),
            HttpStatus.BAD_REQUEST.value()
        );

        return new ResponseEntity<>(exceptionDTO, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception exception, HttpServletRequest request){
        ExceptionDTO exceptionDTO = new ExceptionDTO(
            exception.getMessage(),
            HttpStatus.BAD_REQUEST.value()
        );

        return new ResponseEntity<>(exceptionDTO, HttpStatus.CONFLICT);
    }

}
