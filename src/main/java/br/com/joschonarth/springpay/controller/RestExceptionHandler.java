package br.com.joschonarth.springpay.controller;

import br.com.joschonarth.springpay.exception.SpringPayException;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(SpringPayException.class)
    public ProblemDetail handleSpringPayException(SpringPayException e) {
        return e.toProblemDetail();
    }
}
