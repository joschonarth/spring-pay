package br.com.joschonarth.springpay.service;

import br.com.joschonarth.springpay.controller.dto.TransferDto;
import br.com.joschonarth.springpay.entity.Transfer;
import br.com.joschonarth.springpay.exception.WalletNotFoundException;
import br.com.joschonarth.springpay.repository.TransferRepository;
import br.com.joschonarth.springpay.repository.WalletRepository;
import org.springframework.stereotype.Service;

@Service
public class TransferService {

    private final TransferRepository transferRepository;
    private final AuthorizationService authorizationService;
    private final NotificationService notificationService;
    private final WalletRepository walletRepository;

    public TransferService(TransferRepository transferRepository,
                           AuthorizationService authorizationService,
                           NotificationService notificationService,
                           WalletRepository walletRepository) {
        this.transferRepository = transferRepository;
        this.authorizationService = authorizationService;
        this.notificationService = notificationService;
        this.walletRepository = walletRepository;
    }

    public Transfer transfer(TransferDto transferDto) {

        var sender = walletRepository.findById(transferDto.payer())
                .orElseThrow(() -> new WalletNotFoundException(transferDto.payer()));

        var receiver = walletRepository.findById(transferDto.payee())
                .orElseThrow(() -> new WalletNotFoundException(transferDto.payee()));

        validateTransfer(transferDto, sender);

        return null;
    }
}
