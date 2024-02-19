package com.app.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import com.app.entity.Admin;
import com.app.entity.MyUser;
import com.app.entity.WishlistItem;
import com.app.exception.SomethingWentWrong;
import com.app.service.AdminService;

@RunWith(MockitoJUnitRunner.class)
public class AdminControllerTest {

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
        Authentication auth = null;
        Admin admin = new Admin();
        admin.setEmail("test@example.com");

        when(adminService.findByEmail("test@example.com")).thenReturn(Optional.of(admin));

        // Act
        ResponseEntity<String> response = adminController.logInUserHandler(auth);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
        assertThat(response.getBody()).isEqualTo("test@example.com Logged In Successfully");
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
}
