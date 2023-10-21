package com.sumerge.Auth.Repositories;

import com.sumerge.Auth.Models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class UserRepositoryTest
{
    @Mock
    UserRepository userRepository;

    @Mock
    User user;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindByEmail()
    {
        when(userRepository.findByEmail(anyString())).thenReturn(user);
        User result = userRepository.findByEmail("test@test.com");
        assertEquals(user, result);
        verify(userRepository, times(1)).findByEmail(anyString());
    }

    @Test
    public void testExistsByEmail()
    {
        when(userRepository.existsByEmail(anyString())).thenReturn(true);
        boolean result = userRepository.existsByEmail("test@test.com");
        assertTrue(result);
        verify(userRepository, times(1)).existsByEmail(anyString());
    }
}
