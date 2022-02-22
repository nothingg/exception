package com.example.exception;

import com.example.exception.entity.User;
import com.example.exception.exception.BaseException;
import com.example.exception.service.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestUserService {

	@Autowired
	private UserService userService;

	@Order(1)
	@Test
	void testCreate() throws BaseException {
		User user = userService.create(
			TestCreateData.email,
			TestCreateData.password,
			TestCreateData.name
		);

		//check not null
		Assertions.assertNotNull(user);
		Assertions.assertNotNull(user.getId());

		//check equals
		Assertions.assertEquals(TestCreateData.email,user.getEmail());
		Assertions.assertEquals(TestCreateData.name,user.getName());

		boolean isMatched = userService.matchPassword(TestCreateData.password, user.getPassword());
		Assertions.assertTrue(isMatched);
	}

	@Order(2)
	@Test
	void testUpdate() throws BaseException {
		Optional<User> opt = userService.findByEmail(TestCreateData.email);
		Assertions.assertTrue(opt.isPresent());

		User user = opt.get();

		User userUpdate = userService.updateName(user.getId(), TestUpdateData.name);

		Assertions.assertNotNull(userUpdate);

		Assertions.assertEquals(TestUpdateData.name,userUpdate.getName());
	}

	@Order(3)
	@Test
	void testDelete() {
		Optional<User> opt = userService.findByEmail(TestCreateData.email);
		Assertions.assertTrue(opt.isPresent());

		User user = opt.get();
		userService.deleteById(user.getId());

		Optional<User> byEmail = userService.findByEmail(TestCreateData.email);
		Assertions.assertFalse(byEmail.isPresent());
	}

	interface TestCreateData {
		String email = "na@test.com";
		String password = "1234";
		String name = "ant";
	}

	interface TestUpdateData{
		String name = "bird";
	}

}
