package com.cg.coffeemanagement.controller.api;

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
@RequestMapping("/api/users")
public class UserApi {

    @Autowired
    private AppUtil appUtil;

    @Autowired
    private IUserService userServices;

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id){
        User user = userServices.findById(id).get();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> doCreate(@Validated UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return appUtil.mapErrorToResponse(bindingResult);
        }
        Long id = System.currentTimeMillis() / 1000;
        userDto.setId(id);
        userServices.create(userDto);
        return new ResponseEntity<>(userServices.findById(userDto.getId()).get(), HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> doEdit(@Validated UserDto userDto, @PathVariable Long id ,
                                    BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return appUtil.mapErrorToResponse(bindingResult);
        }
        Optional<User> opUser = userServices.findById(id);
        if (opUser.isPresent()){
            User editUser = userServices.edit(opUser.get() ,userDto);
            return new ResponseEntity<>(editUser, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<?> doDelete(@PathVariable Long id){
        Optional user = userServices.findById(id);
        if (user.isPresent()){
            userServices.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/restore/{id}")
    public ResponseEntity<?> doRestore(@Validated UserDto userDto, @PathVariable Long id ,
                                       BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return appUtil.mapErrorToResponse(bindingResult);
        }
        Optional<User> opUser = userServices.findById(id);
        if (opUser.isPresent()){
            User editUser = userServices.edit(opUser.get() ,userDto);
            userServices.restoreUser(id);
            return new ResponseEntity<>(editUser, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
