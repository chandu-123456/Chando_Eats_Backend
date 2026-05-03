package com.springbootproject.ChandoEats.repository;

import com.springbootproject.ChandoEats.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    @Query("Select u from User u Where u.status='PENDING'")
    public List<User> getPendingRestaurantOwners();

    public User findByEmail(String username);
}
