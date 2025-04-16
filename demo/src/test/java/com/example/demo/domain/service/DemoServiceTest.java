package com.example.demo.domain.service;

import com.example.demo.domain.object.User;
import com.example.demo.domain.object.UserRole;
import com.example.demo.domain.repository.RdsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DemoServiceTest {
    @Mock
    private RdsRepository rds;

    @Spy
    @InjectMocks
    private DemoService demoService;

    // InjectMocks, Assert
    @Test
    void createNewUser() {
        int expected = 1;
        when(rds.create(any(User.class))).thenReturn(1);

        int actual = demoService.createNewUser(new User(1, "user1", "pass1", LocalDate.parse("0001-01-01"), UserRole.USER));

        assertEquals(expected, actual);
    }

    // Spy, Verify
    @Test
    void emailUserInfo_exist_user() {
        when(demoService.readUser(any(Integer.class))).thenReturn(Optional.of(new User(1, "user1", "pass1", LocalDate.parse("0001-01-01"), UserRole.USER)));

        demoService.emailUserInfo(1);

        verify(demoService, times(1)).readUser(any(Integer.class));
        verify(rds, times(1)).read(any(Integer.class));
    }
}
