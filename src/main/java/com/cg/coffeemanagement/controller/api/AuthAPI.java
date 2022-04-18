package com.cg.coffeemanagement.controller.api;

import com.cg.coffeemanagement.model.JwtResponse;
import com.cg.coffeemanagement.model.User;
//import com.cg.coffeemanagement.services.Role.IRoleServices;
import com.cg.coffeemanagement.services.Users.IUserService;
import com.cg.coffeemanagement.services.jwt.JwtService;
import com.cg.coffeemanagement.utils.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthAPI {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private IUserService userService;

//    @Autowired
//    private IRoleServices roleService;

    @Autowired
    private AppUtil appUtils;

//    @PostMapping("/register")
//    public ResponseEntity<?> register(@Valid @RequestBody UserDto userDTO, BindingResult bindingResult) {
//
//        if (bindingResult.hasErrors())
//            return appUtils.mapErrorToResponse(bindingResult);
//
//        Optional<User> optUser = userService.findUserDTOByUsername(userDTO.getUsername());
//
//        if (optUser.isPresent()) {
//            throw new EmailExistsException("Email already exists");
//        }
//
//        Optional<Role> optRole = roleService.findById(optUser.get().getStaff().getPosition().getId());
//
//        if (!optRole.isPresent()) {
//            throw new DataInputException("Invalid account role");
//        }
//
//        try {
//            userService.save(userDTO.toUser());
//
//            return new ResponseEntity<>(HttpStatus.CREATED);
//
//        } catch (DataIntegrityViolationException e) {
//            throw new DataInputException("Account information is not valid, please check the information again");
//        }
//    }

    @PostMapping("/loginManager")
    public ResponseEntity<?> login(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        User userRole = userService.getByUsername(user.getUsername()).get();
        if (userRole.getStaff().getPosition().getPermission().getPermissionAccess() > 2){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtService.generateTokenLogin(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User currentUser = userService.getByUsername(user.getUsername()).get();

        JwtResponse jwtResponse = new JwtResponse(
                jwt,
                currentUser.getId(),
                userDetails.getUsername(),
                currentUser.getUsername(),
                userDetails.getAuthorities()
        );

        ResponseCookie springCookie = ResponseCookie.from("JWT", jwt)
                .httpOnly(false)
                .secure(false)
                .path("/")
                .maxAge(60 * 1000)
//                .domain("localhost")
//                .domain("spb-bank-transaction-jwt.herokuapp.com")
                .domain("hqn-coffee.azurewebsites.net")
                .build();

        System.out.println(jwtResponse);

        return ResponseEntity
                .ok()
                .header(HttpHeaders.SET_COOKIE, springCookie.toString())
                .body(jwtResponse);

    }

    @PostMapping("/loginStaff")
    public ResponseEntity<?> loginStaff(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtService.generateTokenLogin(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User currentUser = userService.getByUsername(user.getUsername()).get();

        JwtResponse jwtResponse = new JwtResponse(
                jwt,
                currentUser.getId(),
                userDetails.getUsername(),
                currentUser.getUsername(),
                userDetails.getAuthorities()
        );

        ResponseCookie springCookie = ResponseCookie.from("JWT", jwt)
                .httpOnly(false)
                .secure(false)
                .path("/")
                .maxAge(60 * 1000)
                .domain("localhost")
//                .domain("spb-bank-transaction-jwt.herokuapp.com")
                .build();

        System.out.println(jwtResponse);

        return ResponseEntity
                .ok()
                .header(HttpHeaders.SET_COOKIE, springCookie.toString())
                .body(jwtResponse);

    }
}
