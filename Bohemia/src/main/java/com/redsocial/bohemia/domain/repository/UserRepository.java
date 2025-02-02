package com.redsocial.bohemia.domain.repository;

import com.redsocial.bohemia.persistence.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByMail(String mail);
    boolean existsByUsername(String username);
    boolean existsByMail(String mail);
}
