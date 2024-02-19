package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.Admin;
import com.app.entity.MyUser;
import com.app.entity.WishlistItem;
import com.app.exception.SomethingWentWrong;
import com.app.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	// login for admin
    
	@PostMapping("/signin")
	public ResponseEntity<String> logInUserHandler(Authentication auth) throws SomethingWentWrong {
	    if (auth == null) {
	        // Handle the case where auth is null
	        return new ResponseEntity<>("Authentication failed", HttpStatus.UNAUTHORIZED);
	    }

	    Admin admin = adminService.findByEmail(auth.getName()).orElseThrow(() -> new SomethingWentWrong("Admin not found"));

	    return new ResponseEntity<>(admin.getEmail() + " Logged In Successfully", HttpStatus.ACCEPTED);
	}
	
	// create id for admin for registration
	
	@PostMapping("/adminregister")
    public ResponseEntity<Admin> registerAdmin(@RequestBody Admin admin) {
		Admin a = adminService.registerAdmin(admin);
		return new ResponseEntity<Admin>(a, HttpStatus.CREATED);
		
    }
	
	
	// get all the user list
	
	@GetMapping("/userlist")
    public ResponseEntity<List<MyUser>> getUsers() {
        try {
            List<MyUser> users = adminService.getUsers();
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (SomethingWentWrong e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	// admin can delete the user by there id

    @DeleteMapping("/deleteuser/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        try {
            adminService.deleteUser(userId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (SomethingWentWrong e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // admin will add the items 
    
    @PostMapping("/createwishlist")
    public ResponseEntity<WishlistItem> createWishlistItem(@RequestBody WishlistItem wishlistItem) {
        try {
            WishlistItem createdWishlistItem = adminService.createWishList(wishlistItem);
            return new ResponseEntity<>(createdWishlistItem, HttpStatus.CREATED);
        } catch (SomethingWentWrong e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // admin can delete the items by id
    
    @DeleteMapping("/deletewishlist/{wishlistId}")
    public ResponseEntity<Void> deleteWishlistItem(@PathVariable Long wishlistId) {
        try {
            adminService.deleteWishlistItem(wishlistId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (SomethingWentWrong e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    // admin can see all the items

    @GetMapping("/allwishlists")
    public ResponseEntity<List<WishlistItem>> getAllWishlistItems() {
        try {
            List<WishlistItem> wishlistItems = adminService.getAllwishListItem();
            return new ResponseEntity<>(wishlistItems, HttpStatus.OK);
        } catch (SomethingWentWrong e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // admin can update the items
    
    @PutMapping("/updatewishlist/{wishlistId}")
    public ResponseEntity<Void> updateWishlistItem(@PathVariable Long wishlistId, @RequestBody WishlistItem wishlistItem) {
        try {
            adminService.updateWishhlist(wishlistId, wishlistItem);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (SomethingWentWrong e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
	

}
