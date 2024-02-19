package com.app.repository;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.entity.MyUser;
import com.app.service.UserServiceImpl;
import com.app.service.UserServiceImplTest;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

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
        user.setEmail("test@gmail.com");

        when(userRepository.findByEmail("test@gmail.com")).thenReturn(Optional.of(user));

        // Act
        Optional<MyUser> result = userService.findByEmail("test@gmail.com");

        // Assert
        assertNotNull(result);
    }
}
