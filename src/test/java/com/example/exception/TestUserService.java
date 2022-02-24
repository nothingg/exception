package com.example.exception;

import com.example.exception.entity.Social;
import com.example.exception.entity.User;
import com.example.exception.exception.BaseException;
import com.example.exception.exception.UserException;
import com.example.exception.service.SocialService;
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

	@Autowired
	private SocialService socialService;

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
	void testSocialCreate() throws UserException {
		Optional<User> opt = userService.findByEmail(TestCreateData.email);
		Assertions.assertTrue(opt.isPresent());

		User user = opt.get();
		Social social = user.getSocial();
		Assertions.assertNull(social);

		social = socialService.createSocial(
				user,
				TestSocialData.facebook,
				TestSocialData.line,
				TestSocialData.instagram,
				TestSocialData.tiktok
		);

		Assertions.assertNotNull(social);
		Assertions.assertEquals(TestSocialData.facebook, social.getFacebook());

	}

	@Order(9)
	@Test
	void testDelete() {
		Optional<User> opt = userService.findByEmail(TestCreateData.email);
		Assertions.assertTrue(opt.isPresent());

		User user = opt.get();
		userService.deleteById(user.getId());

		Optional<User> byEmail = userService.findByEmail(TestCreateData.email);
		Assertions.assertFalse(byEmail.isPresent());
	}

	interface TestSocialData{
		String facebook = "facebook";
		String line = "line";
		String instagram = "instagram";
		String tiktok = "tiktok";
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
