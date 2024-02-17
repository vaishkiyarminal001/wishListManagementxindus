package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.entity.WishlistItem;
import com.app.repositury.WishlistItemRepository;

public class WishlistServiceImpl implements WishlistService{
	
	@Autowired
	private WishlistItemRepository wishlistItemRepository;

	@Override
	public List<WishlistItem> getWishlistItems() {
		return wishlistItemRepository.findAll();
	}

	@Override
	public WishlistItem createWishlistItem(WishlistItem wishlistItem) {
		return wishlistItemRepository.save(wishlistItem);
	}

	@Override
	public void deleteWishlistItem(Long id) {
		wishlistItemRepository.deleteById(id);
		
	}
	
	

}
