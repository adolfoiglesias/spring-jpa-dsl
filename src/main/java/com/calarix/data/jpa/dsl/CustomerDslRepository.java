package com.calarix.data.jpa.dsl;

import com.calarix.data.jpa.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface CustomerDslRepository extends JpaRepository<Customer, Long> ,
        QuerydslPredicateExecutor {

    Customer findByEmailAddress(String emailAddress);
    List<Customer> findByLastname(String lastname, Sort sort);
    Page<Customer> findByFirstname(String firstname, Pageable pageable);

}
