package com.example.usm.service;

import com.example.usm.entity.User;
import com.example.usm.enums.UserType;
import com.example.usm.exception.user.UserNotFoundException;
import com.example.usm.repository.ServiceRepository;
import com.example.usm.repository.UserRepository;
import com.example.usm.service.user.IUserService;
import com.example.usm.service.user.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
public class UserServiceTest {

    private IUserService userService;
    private UserRepository userRepositoryMock;
    private ServiceRepository serviceRepositoryMock;

    private User user;

    public UserServiceTest(){
        userRepositoryMock = Mockito.mock(UserRepository.class);
        serviceRepositoryMock = Mockito.mock(ServiceRepository.class);
        userService = new UserService(userRepositoryMock, serviceRepositoryMock);
    }

    public User initializeUser(){
        return new User("1111-6543-8765-095h", "Nadah",
                "01287446339", UserType.Normal, new ArrayList<>());
    }

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
        this.user = initializeUser();
    }

    @Test(expected = NullPointerException.class)
    public void testAdd_sendNullEntity_returnNullPointerException() {
        /*ACT*/
        userService.add(null);
    }

    @Test
    public void  testAdd_SendValidUserData_ExpectReturnUser(){
        // Arrange
        when(userRepositoryMock.save(any(User.class))).thenReturn(user);
        // ACT
        User returnedUser = userService.add(user);

        // Assert
        assertEquals(user, returnedUser);
        verify(userRepositoryMock, times(1)).save(any(User.class));
    }

    @Test
    public void testGetAll_ExpectEmptyList(){
        // Arrange
        when(userRepositoryMock.findAll()).thenReturn(new ArrayList<>());

        // ACT
        ArrayList<User> users = (ArrayList<User>) userService.getAll();

        // Assert
        assertEquals(0, users.size());
    }

    @Test
    public void testGetAll_SendUserEntity_ExpectOneResult(){
        // Arrange
        when(userRepositoryMock.findAll()).thenReturn(new ArrayList<>(Arrays.asList(user)));

        // ACT
        ArrayList<User> users = (ArrayList<User>) userService.getAll();

        // Assert
        assertEquals(1, users.size());
        assertEquals("Nadah", users.get(0).getName());
    }

    @Test(expected = UserNotFoundException.class)
    public void testFindBySN_sendInvalidSN_ExpectUserNotFoundException(){
        // Arrange
        when(userRepositoryMock.findById(anyString())).thenReturn(Optional.empty());

        //ACT
        userService.findBySN(anyString());
    }

    @Test
    public void testFindBySN_SendValidSN_ExpectReturnUser(){
        //Arrange
        when(userRepositoryMock.findById(anyString())).thenReturn(Optional.of(user));

        //ACT
        User returned = userService.findBySN(user.getSerialNumber());

        // Assert
        assertEquals(user, returned);
        verify(userRepositoryMock, times(1)).findById(anyString());
    }


    @Test(expected = UserNotFoundException.class)
    public void testFindByPhone_sendInvalidValue_ExpectUserNotFoundException(){
        // Arrange
        when(userRepositoryMock.findByPhoneNumber(anyString())).thenReturn(Optional.empty());

        //ACT
        userService.findBySN(anyString());
    }

    @Test
    public void testFindByPhone_SendValidValue_ExpectReturnUser(){
        //Arrange
        when(userRepositoryMock.findByPhoneNumber(anyString())).thenReturn(Optional.of(user));

        //ACT
        User returned = userService.findByPhone(user.getPhoneNumber());

        // Assert
        assertEquals(user, returned);
        verify(userRepositoryMock, times(1)).findByPhoneNumber(anyString());
    }

    @Test
    public void testFindByType_SendNotFoundType_ExpectEmptyList(){
        // Arrange
        when(userRepositoryMock.findByType(any(UserType.class))).thenReturn(new ArrayList<>());

        // Act
        List<User> users = userService.findByType(any(UserType.class));

        //Assert
        assertEquals(0, users.size());
    }

    @Test
    public void testFindByType_SendFoundType_ExpectOneResult(){
        // Arrange
        when(userRepositoryMock.findByType(any(UserType.class))).thenReturn(new ArrayList<>(Arrays.asList(user)));

        // Act
        List<User> users = userService.findByType(user.getType());

        //Assert
        assertEquals(1, users.size());
        assertEquals("Nadah", users.get(0).getName());

    }




}
