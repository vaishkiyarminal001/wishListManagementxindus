package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.entity.MyUser;
import com.app.entity.WishlistItem;
import com.app.exception.SomethingWentWrong;
import com.app.repository.UserRepository;
import com.app.repository.WishlistItemRepository;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private WishlistItemRepository wishlistItemRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	// Create a new user
	@Override
	public MyUser createUser(MyUser user) throws SomethingWentWrong {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	// Delete user by ID
	@Override
	public void deleteUser(Long userId) throws SomethingWentWrong {
		userRepository.deleteById(userId);
	}

	// Update user
	@Override
	@Transactional
	public void updateUser(Long userId, MyUser user) throws SomethingWentWrong {
	    MyUser existingUser = userRepository.findById(userId)
	            .orElseThrow(() -> new SomethingWentWrong("User not found with id: " + userId));

	   
	    existingUser.setUserName(user.getUserName());
	    existingUser.setPassword(user.getPassword());
	    existingUser.setPhoneNumber(user.getPhoneNumber());

	    userRepository.save(existingUser);
	}

	// Get user by ID
	@Override
	public MyUser getUserByUserId(Long userId) throws SomethingWentWrong {
		return userRepository.findById(userId)
				.orElseThrow(() -> new SomethingWentWrong("User not found with id: " + userId));
	}

	// Buy a wishlist item
	@Override
	@Transactional 
	public WishlistItem buyWishListItem(Long userId, Long wishListId) throws SomethingWentWrong {
	    MyUser user = userRepository.findById(userId)
	            .orElseThrow(() -> new SomethingWentWrong("User not found with id: " + userId));
	    WishlistItem wishlistItem = wishlistItemRepository.findById(wishListId)
	            .orElseThrow(() -> new SomethingWentWrong("Wishlist item not found with id: " + wishListId));
	    
	    user.getWishListItem().add(wishlistItem);
	    userRepository.save(user);
	    return wishlistItem;
	}

	// Delete a wishlist item
	@Override
	@Transactional
	public WishlistItem deleteWishListItem(Long userId, Long wishListId) throws SomethingWentWrong {
	    MyUser user = userRepository.findById(userId)
	            .orElseThrow(() -> new SomethingWentWrong("User not found with id: " + userId));
	    WishlistItem wishlistItem = wishlistItemRepository.findById(wishListId)
	            .orElseThrow(() -> new SomethingWentWrong("Wishlist item not found with id: " + wishListId));
	    user.getWishListItem().remove(wishlistItem);
	    userRepository.save(user);

	    return wishlistItem;
	}

	// Find user by email
	public Optional<MyUser> findByEmail(String Email) {
		Optional<MyUser> user= userRepository.findByEmail(Email);
		 if(user.isEmpty()) throw new SomethingWentWrong("No user found");
		 return user;
	}

	@Override
	public List<WishlistItem> getAllwishListItem(Long userId) throws SomethingWentWrong {
		MyUser user= userRepository.findById(userId).get();
		List<WishlistItem> w = user.getWishListItem();
		return w;
		
	}
}
