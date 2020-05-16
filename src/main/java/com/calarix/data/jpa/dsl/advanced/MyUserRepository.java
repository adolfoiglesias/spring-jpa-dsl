package com.calarix.data.jpa.dsl.advanced;

import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;

public interface MyUserRepository extends JpaRepository<MyUser, Long>,
        QuerydslPredicateExecutor<MyUser>

        /* Comentado porque no se genera bien el QMyUser
        , QuerydslBinderCustomizer<QMyUser> {
    @Override
    default public void customize(
            QuerydslBindings bindings, QMyUser root) {
                bindings.bind(String.class)
                .first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
                bindings.excluding(root.email);
    }*/{
}
