package com.app.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

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

import com.app.entity.MyUser;
import com.app.entity.WishlistItem;
import com.app.exception.SomethingWentWrong;
import com.app.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testLogInUserHandler() throws SomethingWentWrong {
        // Arrange
        Authentication auth = null;
        MyUser user = new MyUser();
        user.setEmail("test@example.com");

        when(userService.findByEmail("test@example.com")).thenReturn(Optional.of(user));

        // Act
        ResponseEntity<String> response = userController.logInUserHandler(auth);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
        assertThat(response.getBody()).isEqualTo("test@example.com Logged In Successfully");
    }

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

    @Test
    public void testDeleteUser() throws SomethingWentWrong {
        // Arrange
        Long userId = 1L;

        // Act
        ResponseEntity<Void> response = userController.deleteUser(userId);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

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
