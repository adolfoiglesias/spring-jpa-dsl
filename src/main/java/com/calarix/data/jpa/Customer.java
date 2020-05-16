package com.calarix.data.jpa;

import lombok.Data;
import org.apache.tomcat.jni.Local;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Long id;

    public LocalDate birthday;
    public LocalDate createdAt;
    public String emailAddress;
    public String lastname;
    public String firstname;

    public Customer() {
    }

    public Customer(String firstname, String lastname, String emailAddress, LocalDate birthday ) {

        this.birthday = birthday;
        this.emailAddress = emailAddress;
        this.lastname = lastname;
        this.firstname = firstname;
        this.createdAt = LocalDate.now().minusYears(2l);
    }

    @Override
    public String toString() {
        return firstname + " - " + lastname + " - " + emailAddress + " - " + birthday + " - " + createdAt;
    }
}
