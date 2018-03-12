package com.project.manager.registrationService;

import com.project.manager.JavaFXThreadingRule;
import com.project.manager.entities.UserModel;
import com.project.manager.exceptions.EmailValidationException;
import com.project.manager.repositories.UserRepository;
import com.project.manager.services.RegistrationService;
import org.hibernate.validator.constraints.Email;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private RegistrationService registrationService;



    @Test(expected = EmailValidationException.class)
    public void testRegistrationExpectInvalidEmail(){
        registrationService.registerUser("", "", "" ,"");

    }
}
