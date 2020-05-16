package com.calarix.data.jpa.projection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@RunWith(SpringRunner.class)
public class JpaProjectionTest {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private PersonRepository personRepository;

    @Before
    public void insertData(){

        Address address = new Address("CA",
                "city 1", "street 1", "90001");
        Person person = new Person("John", "Doe",null);
        person.setAddress(address);
        personRepository.save(person);
    }

    @Test
    public void whenUsingClosedProjections_thenViewWithRequiredPropertiesIsReturned() {
        AddressView addressView = addressRepository.getAddressByState("CA").get(0);
        assertThat(addressView.getZipCode()).isEqualTo("90001");

        PersonView personView = addressView.getPerson();
        assertThat(personView.getFirstName()).isEqualTo("John");
        assertThat(personView.getLastName()).isEqualTo("Doe");
    }

}
