package com.simplilearn.sportyshoe.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.simplilearn.sportyshoe.model.User;
import com.simplilearn.sportyshoe.model.WishList;

import java.util.List;
@Repository
public interface WishListRepository extends JpaRepository<WishList, Integer> {

    List<WishList> findAllByUserOrderByCreatedDateDesc(User user);

}
