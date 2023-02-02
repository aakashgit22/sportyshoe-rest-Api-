package com.simplilearn.sportyshoe.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.simplilearn.sportyshoe.model.AuthenticationToken;
import com.simplilearn.sportyshoe.model.User;

@Repository
public interface TokenRepository extends JpaRepository<AuthenticationToken, Integer> {

    AuthenticationToken findByUser(User user);
    AuthenticationToken findByToken(String token);
}
