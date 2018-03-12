package com.project.manager.registration.service;

import com.project.manager.BCryptEncoder;
import com.project.manager.entities.UserModel;
import com.project.manager.exceptions.DifferentPasswordException;
import com.project.manager.exceptions.EmailValidationException;
import com.project.manager.exceptions.UserAlreadyExistException;
import com.project.manager.repositories.UserRepository;
import com.project.manager.services.RegistrationService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private RegistrationService registrationService;

    @Test(expected = EmailValidationException.class)
    public void testRegistrationExpectInvalidEmail(){
        RegistrationService tempRegistrationService = Mockito.spy(registrationService);

        doReturn(false).when(tempRegistrationService).isValidEmailAddress("");
        registrationService.registerUser("", "", "", "");
    }

    @Test(expected = UserAlreadyExistException.class)
    public void testRegistrationExpectUserAlreadyExist()
    {
        UserModel userModel = new UserModel();
        when(userRepository.findByEmail("")).thenReturn(userModel);
        when(userRepository.findByUsername("")).thenReturn(userModel);

        registrationService.registerUser("", "usernam@mail.com", "", "");
    }

    @Test(expected = DifferentPasswordException.class)
    public void testRegistrationExpectDifferentPassword() {
        registrationService.registerUser("","username@mail.com", "password", "otherpass");
    }

    @Test
    public void testRegistration() {
        UserModel userModel = UserModel.builder()
                                        .username("username")
                                        .email("username@mail.com")
                                        .password(BCryptEncoder.encode("password"))
                                        .isFirstLogin(true).build();

        when(userRepository.findByUsername("")).thenReturn(null);
        when(userRepository.findByEmail("")).thenReturn(null);
        when(userRepository.save(userModel)).thenReturn(userModel);

        registrationService.registerUser(userModel.getUsername(), userModel.getEmail(), "password", "password");

        Assert.assertEquals(userModel.getEmail(), userModel.getEmail());
        Assert.assertEquals(userModel.getUsername(), userModel.getUsername());
        Assert.assertTrue(BCryptEncoder.check("password", userModel.getPassword()));
    }

    @Test
    public void testIsValidEmailAddress() {
        boolean result = registrationService.isValidEmailAddress("email");
        boolean result2 = registrationService.isValidEmailAddress("email@");
        boolean result3 = registrationService.isValidEmailAddress("emailmail.pl");
        boolean result4 = registrationService.isValidEmailAddress("email@.pl");
        boolean result5 = registrationService.isValidEmailAddress("email@mail");

        boolean result6 = registrationService.isValidEmailAddress("email@mail.pl");

        Assert.assertFalse(result);
        Assert.assertFalse(result2);
        Assert.assertFalse(result3);
        Assert.assertFalse(result4);
        Assert.assertFalse(result5);

        Assert.assertTrue(result6);
    }
}
