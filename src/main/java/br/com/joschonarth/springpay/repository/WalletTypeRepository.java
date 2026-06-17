package br.com.joschonarth.springpay.repository;

import br.com.joschonarth.springpay.entity.WalletType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletTypeRepository extends JpaRepository<WalletType, Long> {
}
