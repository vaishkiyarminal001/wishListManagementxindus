package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.MyUser;
import com.app.entity.WishlistItem;
import com.app.exception.SomethingWentWrong;
import com.app.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	
	
	@GetMapping("/getUserList")
    public ResponseEntity<List<MyUser>> getUsers() {
        try {
            List<MyUser> users = adminService.getUsers();
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (SomethingWentWrong e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteUserByUserId/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        try {
            adminService.deleteUser(userId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (SomethingWentWrong e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/createWishList")
    public ResponseEntity<WishlistItem> createWishlistItem(@RequestBody WishlistItem wishlistItem) {
        try {
            WishlistItem createdWishlistItem = adminService.createWishList(wishlistItem);
            return new ResponseEntity<>(createdWishlistItem, HttpStatus.CREATED);
        } catch (SomethingWentWrong e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteWishListById/{wishlistId}")
    public ResponseEntity<Void> deleteWishlistItem(@PathVariable Long wishlistId) {
        try {
            adminService.deleteWishlistItem(wishlistId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (SomethingWentWrong e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllWishList")
    public ResponseEntity<List<WishlistItem>> getAllWishlistItems() {
        try {
            List<WishlistItem> wishlistItems = adminService.getAllwishListItem();
            return new ResponseEntity<>(wishlistItems, HttpStatus.OK);
        } catch (SomethingWentWrong e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateWishListById/{wishlistId}")
    public ResponseEntity<Void> updateWishlistItem(@PathVariable Long wishlistId, @RequestBody WishlistItem wishlistItem) {
        try {
            adminService.updateWishhlist(wishlistId, wishlistItem);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (SomethingWentWrong e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	

}
