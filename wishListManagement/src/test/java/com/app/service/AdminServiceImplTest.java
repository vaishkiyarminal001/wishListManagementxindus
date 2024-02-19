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

import com.app.entity.Admin;
import com.app.entity.MyUser;
import com.app.entity.WishlistItem;
import com.app.exception.SomethingWentWrong;
import com.app.repository.AdminRepository;
import com.app.repository.UserRepository;
import com.app.repository.WishlistItemRepository;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class AdminServiceImplTest {

    @Mock
    private AdminRepository adminRepository;

    @Mock
    private WishlistItemRepository wishlistItemRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AdminServiceImpl adminService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindByEmail() {
        // Arrange
        Admin admin = new Admin();
        admin.setAdminId(1L);
        admin.setEmail("test@gmail.com");

        when(adminRepository.findByEmail("test@gmail.com")).thenReturn(Optional.of(admin));

        // Act
        Optional<Admin> result = adminService.findByEmail("test@gmail.com");

        // Assert
        assertNotNull(result);
    }

    @Test
    public void testGetUsers() throws SomethingWentWrong {
        // Arrange
        List<MyUser> users = new ArrayList<>();
        users.add(new MyUser());
        users.add(new MyUser());

        when(userRepository.findAll()).thenReturn(users);

        // Act
        List<MyUser> result = adminService.getUsers();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void testDeleteUser() throws SomethingWentWrong {
        // Arrange
        Long userId = 1L;

        // Act
        adminService.deleteUser(userId);

        // Assert
        // Verify that the deleteById method was called with the correct userId
    }

    @Test
    public void testCreateWishList() throws SomethingWentWrong {
        // Arrange
        WishlistItem wishlistItem = new WishlistItem();

        when(wishlistItemRepository.save(wishlistItem)).thenReturn(wishlistItem);

        // Act
        WishlistItem result = adminService.createWishList(wishlistItem);

        // Assert
        assertNotNull(result);
    }

    @Test
    public void testDeleteWishlistItem() throws SomethingWentWrong {
        // Arrange
        Long wishlistId = 1L;

        // Act
        adminService.deleteWishlistItem(wishlistId);

        // Assert
        // Verify that the deleteById method was called with the correct wishlistId
    }

    @Test
    public void testGetAllwishListItem() throws SomethingWentWrong {
        // Arrange
        List<WishlistItem> wishlistItems = new ArrayList<>();
        wishlistItems.add(new WishlistItem());
        wishlistItems.add(new WishlistItem());

        when(wishlistItemRepository.findAll()).thenReturn(wishlistItems);

        // Act
        List<WishlistItem> result = adminService.getAllwishListItem();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void testUpdateWishhlist() throws SomethingWentWrong {
        // Arrange
        Long wishListId = 1L;
        WishlistItem wishlistItem = new WishlistItem();

        when(wishlistItemRepository.findById(wishListId)).thenReturn(Optional.of(wishlistItem));

        // Act
        adminService.updateWishhlist(wishListId, wishlistItem);

        // Assert
        // Verify that the save method was called with the correct wishlistItem
    }

    @Test
    public void testRegisterAdmin() throws SomethingWentWrong {
        // Arrange
        Admin admin = new Admin();

        when(passwordEncoder.encode(admin.getPassword())).thenReturn("encodedPassword");
        when(adminRepository.save(admin)).thenReturn(admin);

        // Act
        Admin result = adminService.registerAdmin(admin);

        // Assert
        assertNotNull(result);
        assertEquals("encodedPassword", result.getPassword());
    }
}
