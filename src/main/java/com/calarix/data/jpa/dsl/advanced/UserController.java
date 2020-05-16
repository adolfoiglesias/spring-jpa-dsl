package com.calarix.data.jpa.dsl.advanced;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@RestController
public class UserController {
    @Autowired
    private MyUserRepository myUserRepository;

//http://localhost:8080/myusers?search=lastName:doe,age>25
    @RequestMapping(method = RequestMethod.GET, value = "/myusers")
    public Iterable<MyUser> search(@RequestParam(value = "search") String search) {
        MyUserPredicatesBuilder builder = new MyUserPredicatesBuilder();

        if (search != null) {
            Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
            Matcher matcher = pattern.matcher(search + ",");
            while (matcher.find()) {
                builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
            }
        }
        BooleanExpression exp = builder.build();
        return myUserRepository.findAll(exp);
    }
}
