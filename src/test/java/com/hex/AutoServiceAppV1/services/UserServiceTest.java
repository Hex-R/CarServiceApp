package com.hex.AutoServiceAppV1.services;

import com.hex.AutoServiceAppV1.models.Role;
import com.hex.AutoServiceAppV1.models.User;
import com.hex.AutoServiceAppV1.models.UserDetailsForm;
import com.hex.AutoServiceAppV1.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceTest {

    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private MailSenderService mailSenderService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    User createUserWithData() {
        User user = new User();
        user.setUsername("Mark");
        user.setPassword("oldPassword");
        user.setEmail("oldemail@mail.com");
        user.setPhoneNumber("1234567");

        return user;
    }

    @Test
    void addUser() {
        User user = createUserWithData();

        boolean isUserCreated = userService.addUser(user);

        assertTrue(isUserCreated);
        assertFalse(user.isActive());
        assertTrue(is(user.getRoles()).matches(Collections.singleton(Role.USER)));
        assertNotNull(user.getActivationCode());

        verify(userRepository, times(1)).findByUsername(user.getUsername());
        verify(passwordEncoder, times(1)).encode("oldPassword");
        verify(userRepository, times(1)).save(user);
        verify(mailSenderService, times(1))
                .send(
                        eq(user.getEmail()),
                        anyString(),
                        anyString());
    }

    @Test
    void addUserFailTest() {
        User user = createUserWithData();

        doReturn(new User()).when(userRepository).findByUsername("Mark");

        boolean isUserCreated = userService.addUser(user);

        assertFalse(isUserCreated);
        assertFalse(user.isActive());
        assertFalse(is(user.getRoles()).matches(Collections.singleton(Role.USER)));
        assertNull(user.getActivationCode());

        verify(userRepository, times(1)).findByUsername(user.getUsername());
        verify(passwordEncoder, times(0)).encode(anyString());
        verify(userRepository, times(0)).save(any(User.class));
        verify(mailSenderService, times(0))
                .send(
                        anyString(),
                        anyString(),
                        anyString());
    }

    @ParameterizedTest
    @CsvSource({"'newPassword', 'newPassword', 1",
                " , 'oldPassword', 0",
                "'', 'oldPassword', 0"})
    void updateUser(String newPassword,
                    String expectedPassword,
                    int encoderInvocationCount) {

        User user = createUserWithData();

        UserDetailsForm userDto = new UserDetailsForm();
        userDto.setPassword(newPassword);
        userDto.setEmail("newemail@mail.com");
        userDto.setPhoneNumber("7654321");

        doReturn(newPassword).when(passwordEncoder).encode(userDto.getPassword());

        assertTrue(userService.updateUser(user, userDto));
        verify(passwordEncoder, times(encoderInvocationCount)).encode(userDto.getPassword());
        assertEquals(expectedPassword, user.getPassword());
        assertEquals("newemail@mail.com", user.getEmail());
        assertEquals("7654321", user.getPhoneNumber());
        verify(userRepository, times(1)).save(user);
    }

    @ParameterizedTest
    @ValueSource(strings = {"       ", "<6", "0123456789012345678901234567891>30"})
    void updateUserFailTest(String newPassword) {

        User user = createUserWithData();

        UserDetailsForm userDto = new UserDetailsForm();
        userDto.setPassword(newPassword);

        assertFalse(userService.updateUser(user, userDto));
        verify(passwordEncoder, times(0)).encode(userDto.getPassword());
        assertEquals("oldPassword", user.getPassword());
        assertEquals("oldemail@mail.com", user.getEmail());
        assertEquals("1234567", user.getPhoneNumber());
        verify(userRepository, times(0)).save(any(User.class));
    }

    @Test
    void activateUser() {
        User user = createUserWithData();
        user.setActivationCode("code");

        doReturn(user).when(userRepository).findByActivationCode(user.getActivationCode());

        boolean isActivated = userService.activateUser(user.getActivationCode());

        assertTrue(isActivated);
        assertTrue(user.isActive());
        assertNull(user.getActivationCode());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void activateUserFailTest() {
        assertFalse(userService.activateUser("activate"));
        verify(userRepository, times(0)).save(any(User.class));
    }
}