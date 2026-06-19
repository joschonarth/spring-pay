package br.com.joschonarth.springpay.service;

import br.com.joschonarth.springpay.client.AuthorizationClient;
import br.com.joschonarth.springpay.entity.Transfer;
import br.com.joschonarth.springpay.exception.SpringPayException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    private final AuthorizationClient authorizationClient;

    public AuthorizationService(AuthorizationClient authorizationClient) {
        this.authorizationClient = authorizationClient;
    }

    public boolean isAuthorized(Transfer transfer) {

        var resp = authorizationClient.isAuthorized();

        if (resp.getStatusCode().isError()) {
            throw new SpringPayException();
        }

        return resp.getBody().authorized();
    }
}
