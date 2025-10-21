package net.iteams.customerservice.repositories;

import net.iteams.customerservice.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository  extends JpaRepository<Customer,Long> {

}
