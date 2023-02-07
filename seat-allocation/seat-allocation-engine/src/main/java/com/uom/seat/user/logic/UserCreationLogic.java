package com.uom.seat.user.logic;

import com.uom.seat.user.dto.UserRequest;
import com.uom.seat.user.service.UserService;
import com.uom.seat.user.validator.UserValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserCreationLogic {

	private static final Logger logger = Logger.getLogger(UserCreationLogic.class);

	@Autowired
	private UserValidator userValidator;

	@Autowired
	private UserService userService;


	public Integer createUser(String bearerToken, UserRequest user) {
		// 1. validate company request
		// 2. create company

		userValidator.validateUser(user);
		logger.info("The user is validated.");

		Integer id = userService.createUser(user);
		logger.info("The user is created.");

		return id;



	}
}
