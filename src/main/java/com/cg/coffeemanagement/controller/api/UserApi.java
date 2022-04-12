package com.cg.coffeemanagement.controller.api;

import com.cg.coffeemanagement.exception.DataInputException;
import com.cg.coffeemanagement.model.User;
import com.cg.coffeemanagement.model.dto.UserDto;
import com.cg.coffeemanagement.services.Staffs.IStaffServices;
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

    @Autowired
    private IStaffServices staffServices;

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        User user = userServices.findById(id).get();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }



    @PostMapping("/create")
    public ResponseEntity<?> doCreate(@Validated UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return appUtil.mapErrorToResponse(bindingResult);
        }
        if (userServices.existsByUsername(userDto.getUsername())) {
            throw new DataInputException("Tên đăng nhập đã tồn tại");
        }
        Long id = System.currentTimeMillis() / 1000;
        userDto.setId(id);
        userServices.create(userDto);
        staffServices.staffHasUser(userDto.getId_staff());
        return new ResponseEntity<>(userServices.findById(userDto.getId()).get(), HttpStatus.CREATED);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<?> doDelete(@PathVariable Long id) {
        Optional user = userServices.findById(id);
        if (user.isPresent()) {
            userServices.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/restore/{id}")
    public ResponseEntity<?> doRestore(@PathVariable Long id) {
        Optional<User> opUser = userServices.findById(id);
        if (opUser.isPresent()) {
            userServices.restoreUser(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
