package com.lockertracker.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import com.lockertracker.model.UserDBModel;
import com.lockertracker.repository.RoleRepository;
import com.lockertracker.repository.UserRepository;
import com.lockertracker.resources.RoleConsts;
import com.lockertracker.service.exception.registration.PasswordsDoesntMatchException;
import com.lockertracker.service.exception.registration.UsernameAlreadyExistException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

	private UserServiceImpl userServiceImpl;

	@Mock
	private UserDBModel userDBModel;

	@Mock
	private UserRepository userRepository;

	@Mock
	private RoleRepository roleRepository;

	/**
	 * We need to mock the repos; and we always need an userDBModel.
	 */
	@Before
	public void setup() {
		this.userDBModel = new UserDBModel();
		this.userRepository = mock(UserRepository.class);
		this.roleRepository = mock(RoleRepository.class);

		this.userServiceImpl = new UserServiceImpl(userRepository, roleRepository);
	}

	/**
	 * If the two password equal, nothing will happen.
	 * 
	 * @throws PasswordsDoesntMatchException
	 */
	@Test
	public void testCheckTheTwoPasswordThatMustMatch() throws PasswordsDoesntMatchException {
		String password = "testPassword";
		userDBModel.setPassword(password);
		userDBModel.setPasswordAgain(password);

		userServiceImpl.checkTheTwoPasswordThatMustMatch(userDBModel);
	}

	/**
	 * If two passord doesn't match, must throw an PasswordsDoesntMatchException
	 * exception.
	 * 
	 * @throws PasswordsDoesntMatchException
	 */
	@Test(expected = PasswordsDoesntMatchException.class)
	public void testCheckTheTwoPasswordThatMustMatchButDoesntMatch() throws PasswordsDoesntMatchException {
		String password = "testPassword";
		userDBModel.setPassword(password);
		userDBModel.setPasswordAgain(password + "a");

		userServiceImpl.checkTheTwoPasswordThatMustMatch(userDBModel);
	}

	/**
	 * Insert a user with a not exist username.
	 * 
	 * @throws UsernameAlreadyExistException
	 */
	@Test
	public void testCheckUsernameDuplicateInDbBy() throws UsernameAlreadyExistException {
		String userNameThatDoesntExist = "userNameThatDoesntExist";
		userDBModel.setUsername(userNameThatDoesntExist);

		Mockito.when(userRepository.findByUsername(userNameThatDoesntExist)).thenReturn(null);

		userServiceImpl.checkUsernameDuplicateInDbBy(userNameThatDoesntExist);
	}

	/**
	 * Insert a user with an exist username.
	 * 
	 * @throws UsernameAlreadyExistException
	 */
	@Test(expected = UsernameAlreadyExistException.class)
	public void testCheckUsernameDuplicateInDbByAndItWillBeDuplicate() throws UsernameAlreadyExistException {
		String userNameThatAlreadyExist = "userNameThatAlreadyExist";
		userDBModel.setUsername(userNameThatAlreadyExist);

		Mockito.when(userRepository.findByUsername(userNameThatAlreadyExist)).thenReturn(userDBModel);

		userServiceImpl.checkUsernameDuplicateInDbBy(userNameThatAlreadyExist);
	}

	/**
	 * RoleRep, "then(null)", or a full object; but easier to type null. Insert a
	 * user, then assert with the returned object: they had to be the same.
	 * 
	 * @throws DataIntegrityViolationException
	 */
	@Test
	public void testRegisterUser() throws DataIntegrityViolationException {
		Mockito.when(roleRepository.findByRole(RoleConsts.USER)).thenReturn(null);
		Mockito.when(userRepository.save(userDBModel)).thenReturn(userDBModel);

		userDBModel.setUsername("aqweqweqwe");

		UserDBModel userDBModelFromInsert = userServiceImpl.registerUser(userDBModel);

		assertEquals(userDBModel, userDBModelFromInsert);
		assertEquals(userDBModel.getUsername(), userDBModelFromInsert.getUsername());
	}

	/**
	 * RoleRep, "then(null)", or a full object; but easier to type null. Insert a
	 * user, then assert with the returned object: they shouldn't be the same.
	 * 
	 * @throws DataIntegrityViolationException
	 */
	@SuppressWarnings("unchecked")
	@Test(expected = DataIntegrityViolationException.class)
	public void testRegisterUserWithAnAlreadyTakenName() throws DataIntegrityViolationException {
		Mockito.when(roleRepository.findByRole(RoleConsts.USER)).thenReturn(null);
		Mockito.when(userRepository.save(userDBModel)).thenThrow(DataIntegrityViolationException.class);

		userDBModel.setUsername("aqweqweqwe");

		userServiceImpl.registerUser(userDBModel);
	}

}
