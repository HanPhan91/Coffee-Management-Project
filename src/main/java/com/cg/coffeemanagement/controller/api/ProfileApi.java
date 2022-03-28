package com.cg.coffeemanagement.controller.api;

import com.cg.coffeemanagement.Static.Principal;
import com.cg.coffeemanagement.exception.DataInputException;
import com.cg.coffeemanagement.model.User;
import com.cg.coffeemanagement.model.dto.UserDto;
import com.cg.coffeemanagement.services.Users.IUserService;
import com.cg.coffeemanagement.utils.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/profile")
public class ProfileApi {

    @Autowired
    private AppUtil appUtil;

    @Autowired
    private IUserService userServices;

    @PutMapping("/changepass")
    public ResponseEntity<?> doChangePass(@Validated @RequestBody UserDto userDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return appUtil.mapErrorToResponse(bindingResult);
        }
        Optional<User> opUser = userServices.getByUsername(Principal.getPrincipal());
        if (opUser.isPresent()) {
            userServices.changePass(opUser.get().getId(), userDto.getPassword());
            return new ResponseEntity<>(userServices.getByUsername(userDto.getUsername()), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/changeavatar")
    public ResponseEntity<?> doChangeAvatar(@Validated UserDto userDto, BindingResult bindingResult){
        if (userDto.getFile().isEmpty()) {
            throw new DataInputException("Vui lòng chọn ảnh đại diện");
        }
        Optional<User> opUser = userServices.getByUsername(Principal.getPrincipal());
        if (opUser.isPresent()) {
            User editUser = userServices.edit(opUser.get(), userDto);
            return new ResponseEntity<>(editUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
