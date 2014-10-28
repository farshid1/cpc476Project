package com.codeiners.auth;


import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by razorhead on 10/26/14.
 */
public interface UserRepository extends MongoRepository<User, String> {

    public User findByEmail(String email);



}
