package dev.rowand.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.rowand.model.Client;
import dev.rowand.repo.UserDAO;
import dev.rowand.service.UserService;



@ExtendWith(MockitoExtension.class) //used to be @RunWith in junit 4
public class UserServiceTests {
	// an instance of the class we are testing - a REAL instance
	@InjectMocks
	private static UserService userService;
	
	//since we want to test only the functionality of the user service class
	//we will Mock any dependencies that class relies on
	@Mock
	private static UserDAO mockUserDao;


@BeforeAll
public static void setUp() {
	userService = new UserService(new UserDAO());
	
	//this is just a dummy instace that has no actual functionality
	mockUserDao = mock(UserDAO.class);
}

public void should_ReturnAllUsers() {
	//given - 3 users in DB
	List<Client> clientsMock = new ArrayList<>();
	clientsMock.add(new Client(1, "manny", "Mock", "user", "pass"));
	clientsMock.add(new Client(2, "debbie", "Mock", "user", "pass"));
	clientsMock.add(new Client(3, "gigi", "Mock", "user", "pass"));
	//when - UserService's getAllUsers method is called
	when(mockUserDao.getAllUsers()).thenReturn(clientsMock);
	
	//then - it should return all users
	assertEquals(clientsMock, userService.getAllUsers());
}



}
