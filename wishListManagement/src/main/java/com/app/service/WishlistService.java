package com.app.service;

import java.util.List;

import com.app.entity.WishlistItem;

public interface WishlistService {
	
	 List<WishlistItem> getWishlistItems();
	 WishlistItem createWishlistItem(WishlistItem wishlistItem);
	 void deleteWishlistItem(Long id);

}
