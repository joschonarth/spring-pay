package br.com.joschonarth.springpay.service;

import br.com.joschonarth.springpay.controller.dto.CreateWalletDto;
import br.com.joschonarth.springpay.entity.Wallet;
import br.com.joschonarth.springpay.exception.WalletDataAlreadyExistsException;
import br.com.joschonarth.springpay.repository.WalletRepository;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public Wallet creteWallet(CreateWalletDto dto) {

        var walletDb = walletRepository.findByCpfCnpjOrEmail(dto.cpfCnpj(), dto.email());
        if (walletDb.isPresent()) {
            throw new WalletDataAlreadyExistsException("CpfCnpj or Email already exists");
        }

        return walletRepository.save(dto.toWallet());
    }
}
