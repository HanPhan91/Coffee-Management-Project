package com.cg.coffeemanagement.controller.api;

import com.cg.coffeemanagement.model.dto.UserDto;
import com.cg.coffeemanagement.services.Users.IUserServices;
import com.cg.coffeemanagement.utils.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserApi {

    @Autowired
    AppUtil appUtil;

    @Autowired
    private IUserServices userServices;

    @PostMapping("/create")
    public ResponseEntity<?> doCreate(@Validated @RequestBody UserDto userDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return appUtil.mapErrorToResponse(bindingResult);
        }
        Long id = System.currentTimeMillis()/1000;
        userDto.setId(id);

    }
}
