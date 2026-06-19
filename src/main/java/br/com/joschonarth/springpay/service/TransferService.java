package br.com.joschonarth.springpay.service;

import br.com.joschonarth.springpay.controller.dto.TransferDto;
import br.com.joschonarth.springpay.entity.Transfer;
import br.com.joschonarth.springpay.entity.Wallet;
import br.com.joschonarth.springpay.exception.InsufficientBalanceException;
import br.com.joschonarth.springpay.exception.TransferNotAllowedForWalletTypeException;
import br.com.joschonarth.springpay.exception.TransferNotAuthorizedException;
import br.com.joschonarth.springpay.exception.WalletNotFoundException;
import br.com.joschonarth.springpay.repository.TransferRepository;
import br.com.joschonarth.springpay.repository.WalletRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public Transfer transfer(TransferDto transferDto) {

        var sender = walletRepository.findById(transferDto.payer())
                .orElseThrow(() -> new WalletNotFoundException(transferDto.payer()));

        var receiver = walletRepository.findById(transferDto.payee())
                .orElseThrow(() -> new WalletNotFoundException(transferDto.payee()));

        validateTransfer(transferDto, sender);

        sender.debit(transferDto.value());
        receiver.credit(transferDto.value());

        var transfer = new Transfer(sender, receiver, transferDto.value());

        walletRepository.save(sender);
        walletRepository.save(receiver);
        var transferResult = transferRepository.save(transfer);

        return transferResult;
    }

    private void validateTransfer(TransferDto transferDto, Wallet sender) {

        if (!sender.isTransferAllowedForWalletType()) {
            throw new TransferNotAllowedForWalletTypeException();
        }

        if (!sender.isBalancerEqualOrGreatherThan(transferDto.value())) {
            throw new InsufficientBalanceException();
        }

        if (!authorizationService.isAuthorized(transferDto)) {
            throw new TransferNotAuthorizedException();
        }
    }
}
