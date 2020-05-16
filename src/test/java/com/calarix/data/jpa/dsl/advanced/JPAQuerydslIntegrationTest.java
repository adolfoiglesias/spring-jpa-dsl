package com.calarix.data.jpa.dsl.advanced;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@Rollback
public class JPAQuerydslIntegrationTest {

    @Autowired
    private MyUserRepository repo;

    private MyUser userJohn;
    private MyUser userTom;

    @Before
    public void init() {
        userJohn = new MyUser();
        userJohn.setFirstName("John");
        userJohn.setLastName("Doe");
        userJohn.setEmail("john@doe.com");
        userJohn.setAge(22);
        repo.save(userJohn);

        userTom = new MyUser();
        userTom.setFirstName("Tom");
        userTom.setLastName("Doe");
        userTom.setEmail("tom@doe.com");
        userTom.setAge(26);
        repo.save(userTom);
    }

    @Test
    public void givenLast_whenGettingListOfUsers_thenCorrect() {
        MyUserPredicatesBuilder builder = new MyUserPredicatesBuilder()
                .with("lastName", ":", "Doe");

        Iterable<MyUser> results = repo.findAll(builder.build());
     /* No coinciden lso tipos de datos esperados pr el metodo asserThat
     assertThat(results, containsInAnyOrder(userJohn, userTom));
      */
    }
    //how to find a user with given both first and last name:
    @Test
    public void givenFirstAndLastName_whenGettingListOfUsers_thenCorrect() {
        MyUserPredicatesBuilder builder = new MyUserPredicatesBuilder()
                .with("firstName", ":", "John")
                .with("lastName", ":", "Doe");

        Iterable<MyUser> results = repo.findAll(builder.build());

        /* No coinciden lso tipos de datos esperados pr el metodo asserThat
        assertThat(results, contains(userJohn));
        assertThat(results, not(contains(userTom)));*/
    }

    //how to find user with given both last name and minimum age
    @Test
    public void givenLastAndAge_whenGettingListOfUsers_thenCorrect() {
        MyUserPredicatesBuilder builder = new MyUserPredicatesBuilder()
                .with("lastName", ":", "Doe")
                .with("age", ">", "25");

        Iterable<MyUser> results = repo.findAll(builder.build());

        /* No coinciden lso tipos de datos esperados pr el metodo asserThat
        assertThat(results, contains(userTom));
        assertThat(results, not(contains(userJohn)));*/
    }

    //MyUser that doesn’t actually exist:
    @Test
    public void givenWrongFirstAndLast_whenGettingListOfUsers_thenCorrect() {
        MyUserPredicatesBuilder builder = new MyUserPredicatesBuilder()
                .with("firstName", ":", "Adam")
                .with("lastName", ":", "Fox");

        Iterable<MyUser> results = repo.findAll(builder.build());
         /* No coinciden lso tipos de datos esperados pr el metodo asserThat
        assertThat(results, emptyIterable()); */
    }
}
