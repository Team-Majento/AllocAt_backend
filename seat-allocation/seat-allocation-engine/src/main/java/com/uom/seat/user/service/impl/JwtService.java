package com.uom.seat.user.service.impl;

import com.uom.seat.user.entity.JwtRequest;
import com.uom.seat.user.entity.JwtResponse;
import com.uom.seat.user.entity.JwtUtil;
import com.uom.seat.user.entity.UserEntity;
import com.uom.seat.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class JwtService  implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception{
          String userName = jwtRequest.getUserName();
          String userPassword = jwtRequest.getUserPassword();
          authenticate(userName,userPassword);
          final UserDetails userDetails= loadUserByUsername(userName);
          String newGeneratedToken=jwtUtil.generateToken(userDetails);
          UserEntity user=userRepository.findByUserName(userName);

         return new JwtResponse(user,newGeneratedToken);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user=userRepository.findByUserName(username);
        if(username != null){
            return new User(user.getUserName(),user.getPassword(),getAuthorities(user ));
        }else{
            throw new UsernameNotFoundException("username is not valid");
        }
    }

    private Set getAuthorities(UserEntity user){
        Set authorities = new HashSet();
        int k = user.getUserType();
        if(k==1){
            authorities.add(new SimpleGrantedAuthority("ROLE_"+"admin"));
        }
        if(k==2){
            authorities.add(new SimpleGrantedAuthority("ROLE_"+"resourceManager"));
        }
        if(k==3){
            authorities.add(new SimpleGrantedAuthority("ROLE_"+"employee"));
        }
        return  authorities;
    }


    private void authenticate(String userName , String userPassword) throws Exception{
        try{
            System.out.println("****----"+userService.getEncodedPassword(userPassword)+"-------");

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName,userPassword));


        }catch (DisabledException e){
            throw new Exception("user is disabled");
        }catch (BadCredentialsException e){
            throw new Exception("Bad credentials from user");
        }


    }


}
