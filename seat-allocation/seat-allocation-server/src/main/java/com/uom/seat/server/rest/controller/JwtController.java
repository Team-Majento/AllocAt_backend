package com.uom.seat.server.rest.controller;


import com.uom.seat.user.entity.JwtRequest;
import com.uom.seat.user.entity.JwtResponse;
import com.uom.seat.user.service.impl.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class JwtController {

    @Autowired
    private JwtService jwtService;


    @PostMapping({"/authenticate"})
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest)throws Exception{
     return jwtService.createJwtToken(jwtRequest);
    }



}
