package com.project.manager.registrationService;

import com.project.manager.exceptions.EmailValidationException;
import com.project.manager.repositories.UserRepository;
import com.project.manager.services.RegistrationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.doReturn;

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
}
