package br.com.joschonarth.springpay.config;

import br.com.joschonarth.springpay.entity.WalletType;
import br.com.joschonarth.springpay.repository.WalletTypeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DataLoader implements CommandLineRunner {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Arrays.stream(WalletType.Enum.values())
                .forEach(walletType -> {
                    WalletType wt = walletType.get();
                    Long count = (Long) entityManager
                            .createQuery("SELECT COUNT(w) FROM WalletType w WHERE w.description = :desc")
                            .setParameter("desc", wt.getDescription())
                            .getSingleResult();

                    if (count == 0) {
                        entityManager.createNativeQuery("INSERT INTO tb_wallet_type (description) VALUES (:desc)")
                                .setParameter("desc", wt.getDescription())
                                .executeUpdate();
                    }
                });
    }
}