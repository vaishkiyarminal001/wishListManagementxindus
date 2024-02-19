package com.app.service;

import java.util.List;
import java.util.Optional;

import com.app.entity.Admin;
import com.app.entity.MyUser;
import com.app.entity.WishlistItem;
import com.app.exception.SomethingWentWrong;

public interface UserService {
	
	 MyUser createUser(MyUser user) throws SomethingWentWrong;
	 void deleteUser(Long userId) throws SomethingWentWrong;
	 void updateUser(Long userId, MyUser user) throws SomethingWentWrong;
	 MyUser getUserByUserId(Long userId) throws SomethingWentWrong;
	 WishlistItem buyWishListItem(Long userId, Long wishListId) throws SomethingWentWrong;
	 WishlistItem deleteWishListItem(Long userId, Long wishListId) throws SomethingWentWrong;
	 Optional<MyUser >findByEmail(String email);
	 List<WishlistItem> getAllwishListItem(Long userId) throws SomethingWentWrong;
}
