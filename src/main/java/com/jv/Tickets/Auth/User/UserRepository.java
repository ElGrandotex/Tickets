package com.jv.Tickets.Auth.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

//El JpaRepository se debe poner la clase (User) y
//el tipo de identificador (Integer)
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

    @Modifying()
    @Query("update User u set u.firstname=:firstname, u.lastname=:lastname, u.phone=:phone where u.id = :id")
    void updateUser(@Param(value = "id") Integer id, @Param(value = "firstname") String firstname, @Param(value = "lastname") String lastname , @Param(value = "phone") String phone);


}
