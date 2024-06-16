package com.caltayls.nhs_events_subscribers.repository;

import java.util.Optional;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import com.caltayls.nhs_events_subscribers.entity.User;


@EnableScan
public interface UserRepository extends CrudRepository<User, String> {

    public Optional<User> findByEmail(String email);

}