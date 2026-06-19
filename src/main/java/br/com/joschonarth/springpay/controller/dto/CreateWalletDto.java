package br.com.joschonarth.springpay.controller.dto;

import br.com.joschonarth.springpay.entity.Wallet;
import br.com.joschonarth.springpay.entity.WalletType;

public record CreateWalletDto(String fullName,
                              String cpfCnpj,
                              String email,
                              String password,
                              WalletType.Enum walletType) {

    public Wallet toWallet() {
        return new Wallet(
                fullName,
                cpfCnpj,
                email,
                password,
                walletType.get()
        );
    }
}
