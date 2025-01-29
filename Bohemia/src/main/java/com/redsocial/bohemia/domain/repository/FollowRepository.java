package com.redsocial.bohemia.domain.repository;

import com.redsocial.bohemia.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<User, Long> {
    List<User> findByFollowing(User user);
    List<User> findByFollowers(User user);
}
