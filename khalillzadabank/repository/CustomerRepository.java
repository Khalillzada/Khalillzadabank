package com.example.khalillzadabank.repository;
import com.example.khalillzadabank.entitiy.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository <Customer,Long> {
    List<Customer> findAllByActive(Integer active);
    Customer findCustomerByIdAndActive(Long id,Integer active);

}
