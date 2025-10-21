package net.iteams.accountservice;

import net.iteams.accountservice.entities.BankAccount;
import net.iteams.accountservice.enums.AccountType;
import net.iteams.accountservice.repository.BankAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.UUID;
@EnableFeignClients
@SpringBootApplication
public class AccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(BankAccountRepository bankAccountRepository) {
        return args -> {
            BankAccount bankAccount1 = BankAccount.builder()
                    .accountId(UUID.randomUUID().toString())
                    .currency("EUR")
                    .balance(98000)
                    .createdAt(LocalDate.now())
                    .type(AccountType.CURRENT_ACCOUNT)
                    .customerId(Long.valueOf(1))
                    .build();

            BankAccount bankAccount2 = BankAccount.builder()
                    .accountId(UUID.randomUUID().toString())
                    .currency("DNT")
                    .balance(12000)
                    .createdAt(LocalDate.now())
                    .type(AccountType.SAVING_ACCOUNT)
                    .customerId(Long.valueOf(2))
                    .build();
            BankAccount bankAccount3 = BankAccount.builder()
                    .accountId(UUID.randomUUID().toString())
                    .currency("DNT")
                    .balance(1000)
                    .createdAt(LocalDate.now())
                    .type(AccountType.CREDIT_CARD_ACCOUNT)
                    .customerId(Long.valueOf(3))
                    .build();
            bankAccountRepository.save(bankAccount1);
            bankAccountRepository.save(bankAccount2);
            bankAccountRepository.save(bankAccount3);

        };
}


}
