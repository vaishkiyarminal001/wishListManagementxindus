package com.app.service;

import java.util.List;

import com.app.entity.MyUser;
import com.app.entity.WishlistItem;
import com.app.exception.SomethingWentWrong;

public interface AdminService {
	
	List<MyUser> getUsers() throws SomethingWentWrong;
	void deleteUser(Long userId) throws SomethingWentWrong;
	WishlistItem createWishList(WishlistItem wishlistitem) throws SomethingWentWrong;
	void deleteWishlistItem(Long wishlistId) throws SomethingWentWrong;
	List<WishlistItem> getAllwishListItem() throws SomethingWentWrong;
	void updateWishhlist(Long wishListId, WishlistItem wishlistItem) throws SomethingWentWrong;
}
