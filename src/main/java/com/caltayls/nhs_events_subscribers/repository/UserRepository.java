package com.caltayls.nhs_events_subscribers.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.caltayls.nhs_events_subscribers.entity.User;


@Repository("mongoUserRepository")
public interface UserRepository extends MongoRepository<User, String> {

    public Optional<User> findByEmail(String email);

}