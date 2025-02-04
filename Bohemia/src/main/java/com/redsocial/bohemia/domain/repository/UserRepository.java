package com.redsocial.bohemia.domain.repository;

import com.redsocial.bohemia.persistence.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u JOIN u.following f WHERE f.id = :id_user")
    List<User> findUsersFollowing(@Param("id_user") Long userId);
    Optional<User> findByUsername(String username);
    Optional<User> findByMail(String mail);
    boolean existsByUsername(String username);
    boolean existsByMail(String mail);
}
