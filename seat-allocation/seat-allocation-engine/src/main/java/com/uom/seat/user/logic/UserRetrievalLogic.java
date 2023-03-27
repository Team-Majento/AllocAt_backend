package com.uom.seat.user.logic;


import com.uom.seat.user.dto.UserResponse;
import com.uom.seat.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class UserRetrievalLogic {
	
	@Autowired
	private UserService service;

	public UserResponse getUser(String bearerToken, Integer userId) {

		return  service.getUser(userId);
	}


	private void validateServiceParam(Integer id) {
		//TODO
		//ValidationUtil.validateNotEmpty(id, "The id is mandatory");
	}
	public Page<UserResponse> getAllUsers(Integer page, Integer size) {
		return service.getAllUsers(page,size);
	}


    public Boolean userLogin(String userName, String password) {
		return  service.userLogin(userName,password);
    }

    public UserResponse getUserByUserName(String username) {
		return  service.getUserByUserName(username);
    }
}
