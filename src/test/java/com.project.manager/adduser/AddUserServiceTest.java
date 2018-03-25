package com.project.manager.adduser;


import com.project.manager.BCryptEncoder;
import com.project.manager.entities.UserModel;
import com.project.manager.exceptions.EmptyUsernameException;
import com.project.manager.exceptions.NotEnoughPermissionsException;
import com.project.manager.exceptions.UserDoesNotExistException;
import com.project.manager.models.UserRole;
import com.project.manager.repositories.UserRepository;
import com.project.manager.services.AddUserService;
import com.project.manager.services.LoginService;
import com.project.manager.services.RegistrationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AddUserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AddUserService addUserService;
    @InjectMocks
    private LoginService loginService;
    @InjectMocks
    private RegistrationService registrationService;

    @Test(expected = UserDoesNotExistException.class)
    public void testExpectedUserDoesNotExist() {
        addUserService.findUser("usernameasd");
    }

    @Test(expected = EmptyUsernameException.class)
    public void testExpectedEmptyUsername() {
        addUserService.findUser("");
    }

    @Test(expected = NotEnoughPermissionsException.class)
    public void testExpectedInvalidPermission() {
        UserModel userModel = UserModel.builder()
                .username("user")
                .email("username@gmail.com")
                .role(UserRole.USER)
                .password(BCryptEncoder.encode("password"))
                .isFirstLogin(true).build();
        when(userRepository.findByUsername("user")).thenReturn(userModel);
        loginService.loginUser("user","password");
        addUserService.findUser("user");
    }

    @Test
    public void testAddingUserAsAdmin() {
        UserModel userModel = UserModel.builder()
                .username("user")
                .email("username@gmail.com")
                .role(UserRole.ADMIN)
                .password(BCryptEncoder.encode("password"))
                .isFirstLogin(true).build();
        when(userRepository.findByUsername("user")).thenReturn(userModel);
        loginService.loginUser("user","password");
        addUserService.findUser("user");
    }
    @Test
    public void testAddingUserAsClient() {
        UserModel userModel = UserModel.builder()
                .username("user")
                .email("username@gmail.com")
                .role(UserRole.CLIENT)
                .password(BCryptEncoder.encode("password"))
                .isFirstLogin(true).build();
        when(userRepository.findByUsername("user")).thenReturn(userModel);
        loginService.loginUser("user","password");
        addUserService.findUser("user");
    }
}
