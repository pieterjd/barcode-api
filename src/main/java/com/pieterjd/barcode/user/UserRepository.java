package com.pieterjd.barcode.user;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

    @Query("""
            select u
            from User u
            left join fetch u.authorities
            where u.userName = :userName
            """)
    Optional<User> findByUsername(String userName);

}
