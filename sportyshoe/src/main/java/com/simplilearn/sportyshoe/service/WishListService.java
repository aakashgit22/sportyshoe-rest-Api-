package com.simplilearn.sportyshoe.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplilearn.sportyshoe.dto.ProductDto;
import com.simplilearn.sportyshoe.model.User;
import com.simplilearn.sportyshoe.model.WishList;
import com.simplilearn.sportyshoe.repository.WishListRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class WishListService {
	@Autowired
    WishListRepository wishListRepository;

    @Autowired
    ProductService productService;

    public void createWishlist(WishList wishList) {
        wishListRepository.save(wishList);
    }

    public List<ProductDto> getWishListForUser(User user) {
        final List<WishList> wishLists = wishListRepository.findAllByUserOrderByCreatedDateDesc(user);
        List<ProductDto> productDtos = new ArrayList<>();
        for (WishList wishList: wishLists) {
            productDtos.add(productService.getProductDto(wishList.getProduct()));
        }

        return productDtos;
    }
}
