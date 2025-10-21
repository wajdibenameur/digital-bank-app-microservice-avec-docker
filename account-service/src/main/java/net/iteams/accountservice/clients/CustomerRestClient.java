package net.iteams.accountservice.clients;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import net.iteams.accountservice.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerRestClient {

    @GetMapping("/customers/{id}")
    @CircuitBreaker(name="customerService",fallbackMethod = "getDefaultCustomer")
    Customer findCustomerById(@PathVariable("id") Long id);
//
//    @GetMapping("/customers")
//    @CircuitBreaker(name="customerService",fallbackMethod = "getAllCustomers")
//    List<Customer> allCustomers();
@GetMapping("/customers")
@CircuitBreaker(name = "customerService", fallbackMethod = "getDefaultCustomers")
List<Customer> allCustomers();
    default Customer getDefaultCustomer(Long id,Exception exception) {
        Customer customer = new Customer();
        customer.setId(id);
        customer.setFirstName("Not Vailable");
        customer.setLastName("Not Vailable");
        customer.setEmail("Not Vailable");
        return customer;
    }

//    default List<Customer> getAllCustomers(Exception exception) {
//        return List.of();
//    }

// Fallback pour allCustomers
default List<Customer> getDefaultCustomers(Throwable throwable) {
    System.out.println("Fallback allCustomers called : " + throwable.getMessage());
        Customer customer = new Customer();
    customer.setId(-1L);
    customer.setFirstName("Not Available");
    customer.setLastName("Not Available");
    customer.setEmail("Not Available");

    List<Customer> defaultList = new ArrayList<>();
    defaultList.add(customer);
    return defaultList;
}

}
