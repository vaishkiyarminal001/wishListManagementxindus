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
import com.app.repositury.AdminRepository;
import com.app.repositury.UserRepository;
import com.app.repositury.WishlistItemRepository;

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

	@Override
	public List<MyUser> getUsers() throws SomethingWentWrong {
		return userRepository.findAll();
	}

	@Override
	public void deleteUser(Long userId) throws SomethingWentWrong {
		userRepository.deleteById(userId);
	}

	@Override
	public WishlistItem createWishList(WishlistItem wishlistitem) throws SomethingWentWrong {
		return wishlistItemRepository.save(wishlistitem);
	}

	@Override
	public void deleteWishlistItem(Long wishlistId) throws SomethingWentWrong {
		wishlistItemRepository.deleteById(wishlistId);
	}

	@Override
	public List<WishlistItem> getAllwishListItem() throws SomethingWentWrong {
		return wishlistItemRepository.findAll();
	}

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
	
	public Optional<Admin> findByEmail(String Email) {
		Optional<Admin> user= adminRepository.findByEmail(Email);
		 if(user.isEmpty()) throw new SomethingWentWrong("No admin found");
		 return user;
	}

	@Override
	public Admin registerAdmin(Admin admin) throws SomethingWentWrong {
		admin.setPassword(passwordEncoder.encode(admin.getPassword()));
		return adminRepository.save(admin);
	}
	

}
