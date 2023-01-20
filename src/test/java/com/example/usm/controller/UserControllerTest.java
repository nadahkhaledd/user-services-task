package com.example.usm.controller;

import com.example.usm.domain_primitive.PhoneNumber;
import com.example.usm.domain_primitive.SerialNumber;
import com.example.usm.dto.UserDTO;
import com.example.usm.dto.UserResponseDTO;
import com.example.usm.entity.User;
import com.example.usm.enums.UserType;
import com.example.usm.exception.user.UserNotFoundException;
import com.example.usm.service.user.IUserService;
import com.example.usm.service.user.UserService;
import com.example.usm.utility.UserMapping;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserControllerTest {

    private IUserService userServiceMock;
    private UserMapping userMappingMock;

    private UserController userController;

    private User user;
    private UserResponseDTO userResponseDTO;

    public UserControllerTest(){
        userServiceMock = Mockito.mock(UserService.class);
        userMappingMock = Mockito.mock(UserMapping.class);
        userController = new UserController(userServiceMock);
    }

    public void initializeUsers(){
       user = new User("1111-6543-8765-095h", "Nadah",
                "01287446339", UserType.Normal, new ArrayList<>());
       userResponseDTO = new UserResponseDTO(new SerialNumber(user.getSerialNumber()),
               user.getName(), new PhoneNumber(user.getPhoneNumber()),
               user.getType(), user.getServices());
    }

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
        initializeUsers();
    }

    @Test
    public void testGetAll(){
        //Arrange

    }

    @Test
    public void testUserBySerialNumber_SendValidSN_ReturnUser(){
        //Arrange
        when(userServiceMock.findBySN(anyString())).thenReturn(user);
        when(userMappingMock.mapToResponseDTO(any(User.class))).thenReturn(userResponseDTO);

        //ACT
        UserResponseDTO response = userController.userBySerialNumber(anyString());

        //Assert
        assertEquals(userResponseDTO.getName(), response.getName());
        verify(userServiceMock, times(1)).findBySN(anyString());
    }

    @Test(expected = NullPointerException.class)
    public void testAddUser_sendNullObject_expectNullPointerException(){
        //Act
        userController.addUser(null);

        //Assert
        verify(userServiceMock, times(0)).add(any(User.class));
    }

    @Test
    public void testAddUser_ValidData_ExpectReturnUser() {
        //Arrange
        when(userMappingMock.mapToUser(any(UserDTO.class))).thenReturn(user);
        when(userServiceMock.add(any(User.class))).thenReturn(user);
        when(userMappingMock.mapToResponseDTO(any(User.class))).thenReturn(userResponseDTO);
        UserDTO userDTO = new UserDTO(userResponseDTO.getSerialNumber(), userResponseDTO.getName(), userResponseDTO.getPhoneNumber(), userResponseDTO.getServices());

        //Act
        UserResponseDTO response = userController.addUser(userDTO);

        //Assert
        assertEquals(userResponseDTO.getName(), response.getName());
        verify(userServiceMock, times(1)).add(any(User.class));
    }
}
