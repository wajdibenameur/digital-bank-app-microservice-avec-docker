package net.iteams.customerservice;

import net.iteams.customerservice.config.GlobalConfig;
import net.iteams.customerservice.entities.Customer;
import net.iteams.customerservice.repositories.CustomerRepository;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties(value = GlobalConfig.class)
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository) {
      return args -> {
          List<Customer> customersList=List.of(
                  Customer.builder()
                          .firstName("John")
                          .lastName("Smith")
                          .email("john.smith@gmail.com")
                          .build(),
                  Customer.builder()
                          .firstName("ali")
                          .lastName("hani")
                          .email("ali.hani@gmail.com")
                          .build(),
                  Customer.builder()
                          .firstName("mohsen")
                          .lastName("hani")
                          .email("mohsen.hani@gmail.com")
                          .build()


          );
            customerRepository.saveAll(customersList);

      }  ;
    }

}
