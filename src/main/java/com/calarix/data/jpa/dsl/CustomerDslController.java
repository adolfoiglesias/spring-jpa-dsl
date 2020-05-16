package com.calarix.data.jpa.dsl;

import com.calarix.data.jpa.Customer;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("api/customers")
public class CustomerDslController {

    private static final Logger log = LoggerFactory.getLogger(CustomerDslController.class);

    @Autowired
    private CustomerDslRepository customerDslRepository;

    @GetMapping("list/dsl")
    public ResponseEntity<Iterable> findAllConQueryDsl(){
        /**
         * Se comenta porque no se genera bien el QCustomer
         */
        /*
        QCustomer  customer = QCustomer.customer;
        LocalDate today = LocalDate.now();
        BooleanExpression customerHasBirthday = customer.birthday.eq(today);
        BooleanExpression isLongTermCustomer = customer.createdAt.lt(today.minusYears(3));
        BooleanExpression likeFirstname = customer.firstname.like("%Jack%");

        /* no me funciono el filtro con fecha
        Iterable<Customer> customerList = customerRepository
                .findAll(isLongTermCustomer.and(customerHasBirthday));
*/
/*
        Iterable<Customer> customerList = customerDslRepository.findAll(likeFirstname);

        return ResponseEntity.ok(customerList);*/
        return null;
    }
}
