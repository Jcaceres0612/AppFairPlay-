package edu.itm.FairPlay.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ManejadorGlobalExcepciones {

    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<String> manejarNoEncontrado(RecursoNoEncontradoException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND); // 404
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> manejarValidacion(MethodArgumentNotValidException e) {
        String mensaje = e.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST); // 400
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> manejarErrorGeneral(Exception e) {
        return new ResponseEntity<>("Error interno del servidor.", HttpStatus.INTERNAL_SERVER_ERROR); // 500
    }
}
