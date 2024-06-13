package com.fly.wanderflight.log.repository;

import com.fly.wanderflight.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    Optional<Users> findUsersByUserEmail(String userEmail);

    boolean existsByUserEmail(String userEmail);

    boolean existsByUserId(String userId);


}
