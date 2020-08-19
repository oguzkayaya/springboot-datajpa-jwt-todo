package com.ok.todojwtjpademo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ok.todojwtjpademo.models.User;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String name);

    @Query(value = "select c from User c where c.username = ?1")
    List<User> findByUsername2(String name);

}
