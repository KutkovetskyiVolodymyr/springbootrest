package rest.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import rest.entity.User;
import rest.repository.UserRepository;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    UserController sut;
    @Before
    public void setUp() throws Exception {
        //sut = new UserController(userRepository);
    }

    @Test
    public void findAllUsers() throws Exception {
        //testing
        List<User> allUsers = sut.findAllUsers();
        //validate
        verify(userRepository).findAll();
    }

}