package com.app.controller;

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
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/signin")
	public ResponseEntity<String> logInUserHandler(Authentication auth) throws SomethingWentWrong {
		MyUser user = userService.findByEmail(auth.getName()).get();
		return new ResponseEntity<>(user.getEmail() + " Logged In Successfully", HttpStatus.ACCEPTED);
	}
	
	
	@PostMapping("/createUser")
    public ResponseEntity<MyUser> createUser(@RequestBody MyUser user) {
        try {
            MyUser createdUser = userService.createUser(user);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (SomethingWentWrong e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getUserById/{userId}")
    public ResponseEntity<MyUser> getUserById(@PathVariable Long userId) {
        try {
            MyUser user = userService.getUserByUserId(userId);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (SomethingWentWrong e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateUserById/{userId}")
    public ResponseEntity<MyUser> updateUser(@PathVariable Long userId, @RequestBody MyUser user) {
        try {
            userService.updateUser(userId, user);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (SomethingWentWrong e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteUserById/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        try {
            userService.deleteUser(userId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (SomethingWentWrong e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/ByWishListItem/{userId}/wishlist/{wishListId}")
    public ResponseEntity<WishlistItem> buyWishListItem(@PathVariable Long userId, @PathVariable Long wishListId) {
        try {
            WishlistItem wishlistItem = userService.buyWishListItem(userId, wishListId);
            return new ResponseEntity<>(wishlistItem, HttpStatus.OK);
        } catch (SomethingWentWrong e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteWishList/{userId}/wishlist/{wishListId}")
    public ResponseEntity<WishlistItem> deleteWishListItem(@PathVariable Long userId, @PathVariable Long wishListId) {
        try {
            WishlistItem wishlistItem = userService.deleteWishListItem(userId, wishListId);
            return new ResponseEntity<>(wishlistItem, HttpStatus.OK);
        } catch (SomethingWentWrong e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
