package com.app;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.app.controller.AdminController;
import com.app.controller.UserController;
import com.app.entity.Admin;
import com.app.entity.MyUser;
import com.app.entity.WishlistItem;
import com.app.exception.SomethingWentWrong;
import com.app.service.AdminService;
import com.app.service.UserService;

@SpringBootTest
class WishListManagementApplicationTests {

	@Test
	void contextLoads() {
	}
	
	// Admin Testing
	
	 @Mock
	    private AdminService adminService;

	    @InjectMocks
	    private AdminController adminController;

	    @Before
	    public void setUp() {
	        MockitoAnnotations.initMocks(this);
	    }

	    @Test
	    public void testLogInUserHandler() throws SomethingWentWrong {
	        // Arrange
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        Admin admin = new Admin();
	        admin.setEmail("m@gmail.com");

	        when(adminService.findByEmail("min@gmail.com")).thenReturn(Optional.of(admin));

	        // Act
	        ResponseEntity<String> response = adminController.logInUserHandler(auth);

	        // Assert
	        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
	        assertThat(response.getBody()).isEqualTo("Authentication failed");
	    }

	    @Test
	    public void testRegisterAdmin() throws SomethingWentWrong {
	        // Arrange
	        Admin admin = new Admin();

	        when(adminService.registerAdmin(admin)).thenReturn(admin);

	        // Act
	        ResponseEntity<Admin> response = adminController.registerAdmin(admin);

	        // Assert
	        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
	        assertThat(response.getBody()).isEqualTo(admin);
	    }

	    @Test
	    public void testGetUsers() throws SomethingWentWrong {
	        // Arrange
	        // Add necessary setup for adminService.getUsers()

	        // Act
	        ResponseEntity<List<MyUser>> response = adminController.getUsers();

	        // Assert
	        // Add necessary assertions
	    }

	    @Test
	    public void testDeleteUser() throws SomethingWentWrong {
	        // Arrange
	        Long userId = 1L;

	        // Act
	        ResponseEntity<Void> response = adminController.deleteUser(userId);

	        // Assert
	        // Add necessary assertions
	    }

	    @Test
	    public void testCreateWishlistItem() throws SomethingWentWrong {
	        // Arrange
	        WishlistItem wishlistItem = new WishlistItem();

	        // Act
	        ResponseEntity<WishlistItem> response = adminController.createWishlistItem(wishlistItem);

	        // Assert
	        // Add necessary assertions
	    }

	    @Test
	    public void testDeleteWishlistItem() throws SomethingWentWrong {
	        // Arrange
	        Long wishlistId = 1L;

	        // Act
	        ResponseEntity<Void> response = adminController.deleteWishlistItem(wishlistId);

	        // Assert
	        // Add necessary assertions
	    }

	    @Test
	    public void testGetAllWishlistItems() throws SomethingWentWrong {
	        // Arrange
	        // Add necessary setup for adminService.getAllWishlistItems()

	        // Act
	        ResponseEntity<List<WishlistItem>> response = adminController.getAllWishlistItems();

	        // Assert
	        // Add necessary assertions
	    }

	    @Test
	    public void testUpdateWishlistItem() throws SomethingWentWrong {
	        // Arrange
	        Long wishlistId = 1L;
	        WishlistItem wishlistItem = new WishlistItem();

	        // Act
	        ResponseEntity<Void> response = adminController.updateWishlistItem(wishlistId, wishlistItem);

	        // Assert
	        // Add necessary assertions
	    }
	    
	    // User Testing
	    
	    @Mock
	    private UserService userService;

	    @InjectMocks
	    private UserController userController;

//	    @Before
//	    public void setUp() {
//	        MockitoAnnotations.initMocks(this);
//	    }

//	    @Test
//	    public void testLogInUserHandler() throws SomethingWentWrong {
//	        // Arrange
//	        Authentication auth = null;
//	        MyUser user = new MyUser();
//	        user.setEmail("test@example.com");
//
//	        when(userService.findByEmail("test@example.com")).thenReturn(Optional.of(user));
//
//	        // Act
//	        ResponseEntity<String> response = userController.logInUserHandler(auth);
//
//	        // Assert
//	        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
//	        assertThat(response.getBody()).isEqualTo("test@example.com Logged In Successfully");
//	    }

	    @Test
	    public void testCreateUser() throws SomethingWentWrong {
	        // Arrange
	        MyUser user = new MyUser();

	        when(userService.createUser(user)).thenReturn(user);

	        // Act
	        ResponseEntity<MyUser> response = userController.createUser(user);

	        // Assert
	        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
	        assertThat(response.getBody()).isEqualTo(user);
	    }

	    @Test
	    public void testGetUserById() throws SomethingWentWrong {
	        // Arrange
	        Long userId = 1L;
	        MyUser user = new MyUser();

	        when(userService.getUserByUserId(userId)).thenReturn(user);

	        // Act
	        ResponseEntity<MyUser> response = userController.getUserById(userId);

	        // Assert
	        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	        assertThat(response.getBody()).isEqualTo(user);
	    }

	    @Test
	    public void testUpdateUser() throws SomethingWentWrong {
	        // Arrange
	        Long userId = 1L;
	        MyUser user = new MyUser();

	        // Act
	        ResponseEntity<MyUser> response = userController.updateUser(userId, user);

	        // Assert
	        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	    }

//	    @Test
//	    public void testDeleteUser() throws SomethingWentWrong {
//	        // Arrange
//	        Long userId = 1L;
//
//	        // Act
//	        ResponseEntity<Void> response = userController.deleteUser(userId);
//
//	        // Assert
//	        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//	    }

	    @Test
	    public void testBuyWishListItem() throws SomethingWentWrong {
	        // Arrange
	        Long userId = 1L;
	        Long wishListId = 1L;

	        // Act
	        ResponseEntity<WishlistItem> response = userController.buyWishListItem(userId, wishListId);

	        // Assert
	        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	    }

	    @Test
	    public void testDeleteWishListItem() throws SomethingWentWrong {
	        // Arrange
	        Long userId = 1L;
	        Long wishListId = 1L;

	        // Act
	        ResponseEntity<WishlistItem> response = userController.deleteWishListItem(userId, wishListId);

	        // Assert
	        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	    }

}
