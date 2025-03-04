package com.jv.Tickets.Auth.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//El JpaRepository se debe poner la clase (User) y
//el tipo de identificador (Integer)
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

}
