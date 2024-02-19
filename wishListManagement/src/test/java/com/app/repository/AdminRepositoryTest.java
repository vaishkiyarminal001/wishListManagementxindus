package com.app.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.entity.Admin;
import com.app.service.AdminServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class AdminRepositoryTest {
	
	 @Mock
	    private AdminRepository adminRepository;

	    @InjectMocks
	    private AdminServiceImpl adminService;

	    @BeforeEach
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

}
