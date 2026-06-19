package br.com.joschonarth.springpay.controller;

import br.com.joschonarth.springpay.controller.dto.CreateWalletDto;
import br.com.joschonarth.springpay.entity.Wallet;
import br.com.joschonarth.springpay.service.WalletService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WalletController {

    private final WalletService walletService;


    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping("/wallet")
    public ResponseEntity<Wallet> createWallet(@RequestBody CreateWalletDto dto) {

        var wallet = walletService.creteWallet(dto);

        return ResponseEntity.ok(wallet);
    }
}
