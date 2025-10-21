package net.iteams.accountservice.web;

import net.iteams.accountservice.clients.CustomerRestClient;
import net.iteams.accountservice.entities.BankAccount;
import net.iteams.accountservice.model.Customer;
import net.iteams.accountservice.repository.BankAccountRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class AccountRestController {
    private BankAccountRepository accountRepository;
    private CustomerRestClient customerRestClient;

    public AccountRestController(BankAccountRepository accountRepository, CustomerRestClient customerRestClient) {
        this.accountRepository = accountRepository;
        this.customerRestClient = customerRestClient;
    }

    @GetMapping("/accounts")
    public List<BankAccount> accountList() {
        List<BankAccount> accountList = accountRepository.findAll();

        accountList.forEach(acc -> {
            Customer customer = customerRestClient.findCustomerById(acc.getCustomerId());
            acc.setCustomer(customer);
        });

        return accountList;
    }

//    @GetMapping("/accounts")
//    public List<BankAccount> accountList() {
//        List<BankAccount> accounts = accountRepository.findAll();
//        List<Customer> customers = customerRestClient.allCustomers();
//
//        if (customers.size() == 1 && customers.get(0).getId() == -1L) {
//            // fallback activé → tous les clients sont indisponibles
//            Customer fallbackCustomer = customers.get(0);
//            accounts.forEach(account -> account.setCustomer(fallbackCustomer));
//            return accounts;
//        }
//
//        Map<Long, Customer> customerMap = customers.stream()
//                .collect(Collectors.toMap(Customer::getId, customer -> customer));
//
//        for (BankAccount account : accounts) {
//            Customer customer = customerMap.get(account.getCustomerId());
//            if (customer != null) {
//                account.setCustomer(customer);
//            }
//        }
//        return accounts;
//    }


    @GetMapping("/accounts/{id}")
    public BankAccount bankAccountById(@PathVariable String id) {
        BankAccount bankAccount= accountRepository.findById(id).get();
        Customer customer=customerRestClient.findCustomerById(bankAccount.getCustomerId());
        bankAccount.setCustomer(customer);
        return bankAccount;
    }


}
