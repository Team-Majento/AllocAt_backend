package com.uom.seat.server.rest.controller;


import com.uom.seat.api.UserApi;
import com.uom.seat.user.dto.UserRequest;
import com.uom.seat.user.dto.UserResponse;
import com.uom.seat.util.AccessTokenUtil;
import io.swagger.annotations.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/users")
@Api(description = "The user API for user management tasks")

public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserApi userApi;

    @ApiOperation(value = "Register user.", response = Integer.class, consumes = "application/json")
    @ApiResponses(value = {
            // @formatter:off
            @ApiResponse(code = 201, message = "The user is created and return user id."),
            @ApiResponse(code = 400, message = "Invalid API argument.")
            // @formatter:on
    })
    @PostMapping()
    public ResponseEntity<Integer> registerUser(
         //   @ApiParam(value = "Bearer access token", required = true) @RequestHeader(HttpHeaders.AUTHORIZATION) final String authorization,
            @ApiParam(value = "JSON format of the user request.", required = true) @RequestBody final UserRequest user,
            UriComponentsBuilder builder) {

        ResponseEntity<Integer> responseEntity = null;
        logger.info("Register user request is received.");
        logger.debug("Register user request" + user.toString());

        Integer userId = userApi.createCompany(AccessTokenUtil.getBearerToken("authorization"),
               user);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("users/{userId}").buildAndExpand(userId).toUri());

        responseEntity = new ResponseEntity<Integer>(userId, headers, HttpStatus.CREATED);
        return responseEntity;
    }



    @ApiOperation(value = "get user by id.", response = UserResponse.class, produces = "application/json")
    @GetMapping("{userId}")
    public ResponseEntity<UserResponse> getCompany(
           // @ApiParam(value = "Bearer access token", required = true) @RequestHeader(HttpHeaders.AUTHORIZATION) final String authorization,
            @PathVariable("userId") final Integer userId) {

        ResponseEntity<UserResponse> responseEntity = null;
        logger.info("Get user by id request is received.");
        UserResponse user = userApi
                .getUser(AccessTokenUtil.getBearerToken("authorization"), userId);

        responseEntity = new ResponseEntity<UserResponse>(user, HttpStatus.OK);
        return responseEntity;
    }

    @ApiOperation(value = "Update user by userId.", response = UserResponse.class, produces = "application/json")
    @PutMapping("{userId}")
    public ResponseEntity<UserResponse> getCompany(
            //@ApiParam(value = "Bearer access token", required = true) @RequestHeader(HttpHeaders.AUTHORIZATION) final String authorization,
            @PathVariable("userId") final Integer userId, @RequestBody final UserRequest userRequest) {

        ResponseEntity<UserResponse> responseEntity = null;
        logger.info("Get company by id request is received.");

        UserResponse response = userApi.updateUser(userId, userRequest);
        responseEntity = new ResponseEntity<UserResponse>(response, HttpStatus.OK);
        return responseEntity;
    }

}
