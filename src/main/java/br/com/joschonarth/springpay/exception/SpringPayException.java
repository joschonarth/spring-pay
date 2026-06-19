package br.com.joschonarth.springpay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class SpringPayException extends RuntimeException {

    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        pb.setTitle("SpringPay internal server error");

        return pb;
    }
}
