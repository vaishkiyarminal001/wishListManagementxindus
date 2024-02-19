package com.app.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.app.entity.MyUser;
import com.app.entity.WishlistItem;
import com.app.exception.SomethingWentWrong;
import com.app.repository.UserRepository;
import com.app.repository.WishlistItemRepository;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private WishlistItemRepository wishlistItemRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindByEmail() {
        // Arrange
        MyUser user = new MyUser();
        user.setUserId(1L);
        user.setEmail("test@example.com");

        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));

        // Act
        Optional<MyUser> result = userService.findByEmail("test@example.com");

        // Assert
        assertNotNull(result);
    }

    @Test
    public void testCreateUser() throws SomethingWentWrong {
        // Arrange
        MyUser user = new MyUser();

        when(passwordEncoder.encode(user.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(user)).thenReturn(user);

        // Act
        MyUser result = userService.createUser(user);

        // Assert
        assertNotNull(result);
        assertEquals("encodedPassword", result.getPassword());
    }

    @Test
    public void testDeleteUser() throws SomethingWentWrong {
        // Arrange
        Long userId = 1L;

        // Act
        userService.deleteUser(userId);

        // Assert
        // Verify that the deleteById method was called with the correct userId
    }

    @Test
    public void testUpdateUser() throws SomethingWentWrong {
        // Arrange
        Long userId = 1L;
        MyUser user = new MyUser();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Act
        userService.updateUser(userId, user);

        // Assert
        // Verify that the save method was called with the correct user
    }

    @Test
    public void testGetUserByUserId() throws SomethingWentWrong {
        // Arrange
        Long userId = 1L;
        MyUser user = new MyUser();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Act
        MyUser result = userService.getUserByUserId(userId);

        // Assert
        assertNotNull(result);
    }

    @Test
    public void testBuyWishListItem() throws SomethingWentWrong {
        // Arrange
        Long userId = 1L;
        Long wishListId = 1L;
        MyUser user = new MyUser();
        WishlistItem wishlistItem = new WishlistItem();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(wishlistItemRepository.findById(wishListId)).thenReturn(Optional.of(wishlistItem));

        // Act
        WishlistItem result = userService.buyWishListItem(userId, wishListId);

        // Assert
        assertNotNull(result);
    }

    @Test
    public void testDeleteWishListItem() throws SomethingWentWrong {
        // Arrange
        Long userId = 1L;
        Long wishListId = 1L;
        MyUser user = new MyUser();
        WishlistItem wishlistItem = new WishlistItem();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(wishlistItemRepository.findById(wishListId)).thenReturn(Optional.of(wishlistItem));

        // Act
        WishlistItem result = userService.deleteWishListItem(userId, wishListId);

        // Assert
        assertNotNull(result);
    }
}
