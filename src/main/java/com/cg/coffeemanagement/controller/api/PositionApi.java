package com.cg.coffeemanagement.controller.api;

import com.cg.coffeemanagement.exception.DataInputException;
import com.cg.coffeemanagement.model.Position;
import com.cg.coffeemanagement.services.Positions.IPositionServices;
import com.cg.coffeemanagement.utils.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/positions")
public class PositionApi {

    @Autowired
    private AppUtil appUtil;

    @Autowired
    private IPositionServices positionServices;


    @GetMapping("/{id}")
    public ResponseEntity<?> getPosition(@PathVariable Long id) {
        Optional<Position> position = positionServices.findById(id);
        if (position.isPresent()) {
            return new ResponseEntity<>(position.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> doCreate(@Validated @RequestBody Position position, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return appUtil.mapErrorToResponse(bindingResult);
        } else {
            Position creaPosition = positionServices.save(position);
            return new ResponseEntity<>(creaPosition, HttpStatus.CREATED);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> doEdit(@Validated @RequestBody Position position, @PathVariable Long id,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return appUtil.mapErrorToResponse(bindingResult);
        } else {
            Optional<Position> opPosition = positionServices.findById(id);
            if (opPosition.isPresent()) {
                Position editPosition = opPosition.get();
                editPosition.setName(position.getName());
                positionServices.save(editPosition);
                return new ResponseEntity<>(positionServices.findById(id).get(), HttpStatus.OK);
            } else {
                throw new DataInputException("Chức vụ không tồn tại");
            }
        }
    }
}
