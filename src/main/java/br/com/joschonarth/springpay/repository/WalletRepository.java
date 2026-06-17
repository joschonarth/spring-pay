package br.com.joschonarth.springpay.repository;

import br.com.joschonarth.springpay.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
}
