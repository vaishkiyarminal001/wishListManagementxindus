package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.WishlistItem;
import com.app.repositury.WishlistItemRepository;
import com.app.service.WishlistService;

@RestController
@RequestMapping("/api/wishlists")
public class WishlistController {
	
	@Autowired
    private WishlistService wishlistService;

	@GetMapping
    public List<WishlistItem> getWishlistItems() {
        return wishlistService.getWishlistItems();
    }

    @PostMapping
    public WishlistItem createWishlistItem(@RequestBody WishlistItem wishlistItem) {
        return wishlistService.createWishlistItem(wishlistItem);
    }

    @DeleteMapping("/{id}")
    public void deleteWishlistItem(@PathVariable Long id) {
        wishlistService.deleteWishlistItem(id);
    }

}
