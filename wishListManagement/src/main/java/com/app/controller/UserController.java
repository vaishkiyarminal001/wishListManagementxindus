package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
import com.app.service.UserService;

@RestController
@RequestMapping("/api/wishlist")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	// user can login 
	
	@PostMapping("/signin")
	public ResponseEntity<String> logInUserHandler(Authentication auth) throws SomethingWentWrong {
		MyUser user = userService.findByEmail(auth.getName()).get();
		return new ResponseEntity<>(user.getEmail() + " Logged In Successfully", HttpStatus.ACCEPTED);
	}
	
	// user can create the account 
	
	@PostMapping("/")
    public ResponseEntity<MyUser> createUser(@RequestBody MyUser user) {
        try {
            MyUser createdUser = userService.createUser(user);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (SomethingWentWrong e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	

	// see the user by id
	
    @GetMapping("/{userId}")
    public ResponseEntity<MyUser> getUserById(@PathVariable Long userId) {
        try {
            MyUser user = userService.getUserByUserId(userId);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (SomethingWentWrong e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // user can update there account

    @PutMapping("/{userId}")
    public ResponseEntity<MyUser> updateUser(@PathVariable Long userId, @RequestBody MyUser user) {
        try {
            userService.updateUser(userId, user);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (SomethingWentWrong e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    // user can delete there account

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        try {
            userService.deleteUser(userId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (SomethingWentWrong e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    // user can add items 

    @PostMapping("/{userId}/{wishListId}")
    public ResponseEntity<WishlistItem> buyWishListItem(@PathVariable Long userId, @PathVariable Long wishListId) {
        try {
            WishlistItem wishlistItem = userService.buyWishListItem(userId, wishListId);
            return new ResponseEntity<>(wishlistItem, HttpStatus.OK);
        } catch (SomethingWentWrong e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    //user can delete there wishlist item

    @DeleteMapping("/{userId}/{wishListId}")
    public ResponseEntity<WishlistItem> deleteWishListItem(@PathVariable Long userId, @PathVariable Long wishListId) {
        try {
            WishlistItem wishlistItem = userService.deleteWishListItem(userId, wishListId);
            return new ResponseEntity<>(wishlistItem, HttpStatus.OK);
        } catch (SomethingWentWrong e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    // user and see the wishlist items
    
    @GetMapping("/getlist/{userId}")
    public ResponseEntity <?> getList(@PathVariable Long userId){
    	try {
    		List<WishlistItem> wishListSee = userService.getAllwishListItem(userId);
    		return new ResponseEntity<List<WishlistItem>>(wishListSee, HttpStatus.ACCEPTED);
    		
    	}catch(SomethingWentWrong e) {
    		return new ResponseEntity<String>("Id is wrong for items", HttpStatus.BAD_GATEWAY);
    		
    	}
    }

}
