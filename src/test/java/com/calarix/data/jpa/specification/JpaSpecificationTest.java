package com.calarix.data.jpa.specification;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.Matchers.isIn;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@DataJpaTest
public class JpaSpecificationTest {

    @Autowired
    private UserRepository repository;

    private User userJohn;
    private User userTom;

    @Before
    public void init() {
        userJohn = new User();
        userJohn.setFirstName("John");
        userJohn.setLastName("Doe");
        userJohn.setEmail("john@doe.com");
        userJohn.setAge(22);
        repository.save(userJohn);

        userTom = new User();
        userTom.setFirstName("Tom");
        userTom.setLastName("Doe");
        userTom.setEmail("tom@doe.com");
        userTom.setAge(26);
        repository.save(userTom);
    }

    @Test
    public void givenLast_whenGettingListOfUsers_thenCorrect() {
        UserSpecification spec =
                new UserSpecification(new SearchCriteria("lastName", ":", "doe"));

        List<User> results = repository.findAll(spec);

        assertThat(userJohn, isIn(results));
        assertThat(userTom, isIn(results));
    }
    // where , > and like
    @Test
    public void givenLastAndAge_whenGettingListOfUsers_thenCorrect() {
        UserSpecification spec1 =
                new UserSpecification(new SearchCriteria("age", ">", "25"));
        UserSpecification spec2 =
                new UserSpecification(new SearchCriteria("lastName", ":", "doe"));

        List<User> results =
                repository.findAll(Specification.where(spec1).and(spec2));

        assertThat(userTom, isIn(results));
        assertThat(userJohn, not(isIn(results)));
    }

    @Test
    public void givenWrongFirstAndLast_whenGettingListOfUsers_thenCorrect() {
        UserSpecification spec1 =
                new UserSpecification(new SearchCriteria("firstName", ":", "Adam"));
        UserSpecification spec2 =
                new UserSpecification(new SearchCriteria("lastName", ":", "Fox"));

        List<User> results =
                repository.findAll(Specification.where(spec1).and(spec2));

        assertThat(userJohn, not(isIn(results)));
        assertThat(userTom, not(isIn(results)));
    }



}
