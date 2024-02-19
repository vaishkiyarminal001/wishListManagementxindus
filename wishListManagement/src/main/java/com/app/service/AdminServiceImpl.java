package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.entity.Admin;
import com.app.entity.MyUser;
import com.app.entity.WishlistItem;
import com.app.exception.SomethingWentWrong;
import com.app.repository.AdminRepository;
import com.app.repository.UserRepository;
import com.app.repository.WishlistItemRepository;

import jakarta.transaction.Transactional;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private WishlistItemRepository wishlistItemRepository;
	
	@Autowired
	private UserRepository userRepository;

	// Get all users
	@Override
	public List<MyUser> getUsers() throws SomethingWentWrong {
		return userRepository.findAll();
	}

	// Delete user by ID
	@Override
	public void deleteUser(Long userId) throws SomethingWentWrong {
		userRepository.deleteById(userId);
	}

	// Create a new wishlist item
	@Override
	public WishlistItem createWishList(WishlistItem wishlistitem) throws SomethingWentWrong {
		return wishlistItemRepository.save(wishlistitem);
	}

	// Delete a wishlist item by ID
	@Override
	public void deleteWishlistItem(Long wishlistId) throws SomethingWentWrong {
		wishlistItemRepository.deleteById(wishlistId);
	}

	// Get all wishlist items
	@Override
	public List<WishlistItem> getAllwishListItem() throws SomethingWentWrong {
		return wishlistItemRepository.findAll();
	}

	// Update a wishlist item
	@Override
	@Transactional
	public void updateWishhlist(Long wishListId, WishlistItem wishlistItem) throws SomethingWentWrong {
		WishlistItem existingWishlistItem = wishlistItemRepository.findById(wishListId)
				.orElseThrow(() -> new SomethingWentWrong("Wishlist item not found with id: " + wishListId));
		
		existingWishlistItem.setName(wishlistItem.getName());
		existingWishlistItem.setDescription(wishlistItem.getDescription());
		existingWishlistItem.setPrice(wishlistItem.getPrice());
		
		wishlistItemRepository.save(existingWishlistItem);
	}
	
	// Find admin by email
	public Optional<Admin> findByEmail(String Email) {
		Optional<Admin> user= adminRepository.findByEmail(Email);
		 if(user.isEmpty()) throw new SomethingWentWrong("No admin found");
		 return user;
	}

	// Register a new admin
	@Override
	public Admin registerAdmin(Admin admin) throws SomethingWentWrong {
		admin.setPassword(passwordEncoder.encode(admin.getPassword()));
		return adminRepository.save(admin);
	}
}
