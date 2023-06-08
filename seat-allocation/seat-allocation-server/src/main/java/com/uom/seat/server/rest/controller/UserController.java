package com.uom.seat.server.rest.controller;


import com.uom.seat.api.UserApi;
import com.uom.seat.resource.dto.ResourceResponse;
import com.uom.seat.user.dto.UserRequest;
import com.uom.seat.user.dto.UserResponse;
import com.uom.seat.user.service.UserService;
import com.uom.seat.util.AccessTokenUtil;
import io.swagger.annotations.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/users")
@Api(description = "The user API for user management tasks")

public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserApi userApi;

    @Autowired
    private UserService userService;

    @PostConstruct
    public void initRolesAndUsers(){
        userService.initRolesAndUser();
    }



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
    @CrossOrigin
    @GetMapping("/get/{userId}")
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
    @CrossOrigin
    @PutMapping("/update/{userId}")
    public ResponseEntity<UserResponse> getUser(
            //@ApiParam(value = "Bearer access token", required = true) @RequestHeader(HttpHeaders.AUTHORIZATION) final String authorization,
            @PathVariable("userId") final Integer userId, @RequestBody final UserRequest userRequest) {

        ResponseEntity<UserResponse> responseEntity = null;
        logger.info("Get user by id request is received.");

        UserResponse response = userApi.updateUser(userId, userRequest);
        responseEntity = new ResponseEntity<UserResponse>(response, HttpStatus.OK);
        return responseEntity;
    }

    @ApiOperation(value="Get all Users",response = UserResponse.class,produces = "application/json")
    @GetMapping("users-page")
    public ResponseEntity<Page<UserResponse>> getAllUsers(
            //  @ApiParam(value = "Bearer access token", required = true) @RequestHeader(HttpHeaders.AUTHORIZATION) final String authorization,
            @RequestParam(name = "page" ,defaultValue ="0" ,required = false)Integer page,
            @RequestParam(name = "size" ,defaultValue ="10" ,required = false)Integer size
            //@RequestParam(value = "sortBy" ,required = false) ResourceSortFiled sortBy,
            // @RequestParam(name = "direction" ,required = false) Sort.Direction direction
    ){

        ResponseEntity<Page<UserResponse>> responseEntity = null;

        Page<UserResponse> pageDtos = userApi.getAllUsers( AccessTokenUtil.getBearerToken("authorization"), page,size);
        responseEntity=new ResponseEntity<Page<UserResponse>>(pageDtos,HttpStatus.OK);
        return responseEntity;
    }


    @ApiOperation(value="Delete user by ID",response = ResourceResponse.class,produces = "application/json")
    @DeleteMapping("{userId}")
    public ResponseEntity<Boolean> deleteUser(
            //  @ApiParam(value = "Bearer access token", required = true) @RequestHeader(HttpHeaders.AUTHORIZATION) final String authorization,
            @PathVariable("userId") final Integer userId) {

        ResponseEntity<Boolean> responseEntity = null;
        logger.info("Delete user by id request is received.");
        Boolean result = userApi.deleteUser(AccessTokenUtil.getBearerToken("authorization"), userId);
        responseEntity = new ResponseEntity<Boolean>(result, HttpStatus.OK);
        return responseEntity;
    }


    @ApiOperation(value = "user logging.", response = UserResponse.class, produces = "application/json")
    @PostMapping("{userName}/{password}")
    public ResponseEntity<Boolean> userLogin(
            // @ApiParam(value = "Bearer access token", required = true) @RequestHeader(HttpHeaders.AUTHORIZATION) final String authorization,
            @PathVariable("userName") final String userName,@PathVariable("password") final String password) {
        ResponseEntity<Boolean> responseEntity = null;
        logger.info("Get user by id request is received.");
       Boolean status = userApi
                .userLogin(userName,password);
       if(status)
        responseEntity = new ResponseEntity<Boolean>(status, HttpStatus.OK);
       else
           responseEntity = new ResponseEntity<Boolean>(status, HttpStatus.BAD_REQUEST);

        return responseEntity;
    }

    @ApiOperation(value = "get user by UserName.", response = UserResponse.class, produces = "application/json")
    @CrossOrigin
    @GetMapping("/getuser/{username}")
    public ResponseEntity<UserResponse> getUserByUserName(
            // @ApiParam(value = "Bearer access token", required = true) @RequestHeader(HttpHeaders.AUTHORIZATION) final String authorization,
            @PathVariable("username") final String username) {

        ResponseEntity<UserResponse> responseEntity = null;
        logger.info("Get user by username request is received.");
        UserResponse user = userApi
                .getUserByUserName(AccessTokenUtil.getBearerToken("authorization"), username);

        responseEntity = new ResponseEntity<UserResponse>(user, HttpStatus.OK);
        return responseEntity;
    }







}
