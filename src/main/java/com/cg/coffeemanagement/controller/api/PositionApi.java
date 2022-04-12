package com.cg.coffeemanagement.controller.api;

import com.cg.coffeemanagement.Static.Principal;
import com.cg.coffeemanagement.exception.DataInputException;
import com.cg.coffeemanagement.model.Permission;
import com.cg.coffeemanagement.model.Position;
import com.cg.coffeemanagement.model.User;
import com.cg.coffeemanagement.model.dto.PositionDto;
import com.cg.coffeemanagement.services.Permission.IPermissionServices;
import com.cg.coffeemanagement.services.Positions.IPositionServices;
import com.cg.coffeemanagement.services.Users.IUserService;
import com.cg.coffeemanagement.utils.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/positions")
public class PositionApi {

    @Autowired
    private AppUtil appUtil;

    @Autowired
    private IPositionServices positionServices;

    @Autowired
    private IPermissionServices permissionServices;

    @Autowired
    private IUserService userServices;

    @GetMapping
    public ResponseEntity<?> getAllPosition() {
        User user = userServices.getByUsername(Principal.getPrincipal()).get();
        List<Position> positions = positionServices.findPositionNotDeletedAndPermissionSmaller(user.getStaff().getPosition().getPermission().getPermissionAccess());
        return new ResponseEntity<>(positions, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getPosition(@PathVariable Long id) {
        Optional<Position> position = positionServices.findById(id);
        if (position.isPresent()) {
            return new ResponseEntity<>(position.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> doCreate(@Validated @RequestBody PositionDto positionDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return appUtil.mapErrorToResponse(bindingResult);
        }
        Position creaPosition = new Position();
        creaPosition.setName(positionDto.getName());
        Permission permission = permissionServices.findById(positionDto.getPermission()).get();
        creaPosition.setPermission(permission);
        Position returnPosition = positionServices.save(creaPosition);
        return new ResponseEntity<>(returnPosition, HttpStatus.CREATED);

    }

    @PutMapping("/edit")
    public ResponseEntity<?> doEdit(@Validated @RequestBody PositionDto positionDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return appUtil.mapErrorToResponse(bindingResult);
        }
        Optional<Position> opPosition = positionServices.findById(positionDto.getId());
        if (opPosition.isPresent()) {
            Position editPosition = opPosition.get();
            editPosition.setName(positionDto.getName());
            positionServices.save(editPosition);
            return new ResponseEntity<>(positionServices.findById(positionDto.getId()).get(), HttpStatus.OK);
        } else {
            throw new DataInputException("Chức vụ không tồn tại");
        }

    }

    @PutMapping("/delete")
    public ResponseEntity<?> doDelete(@RequestBody PositionDto positionDto) {
        Optional<Position> optionalPosition = positionServices.findById(positionDto.getId());
        if (optionalPosition.isPresent()) {
            positionServices.deletePosition(positionDto.getId());
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            throw new DataInputException("Chức vụ không tồn tại");
        }
    }

    @PutMapping("/restore")
    public ResponseEntity<?> doRestore(@Validated @RequestBody PositionDto positionDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return appUtil.mapErrorToResponse(bindingResult);
        }
        Optional<Position> opPosition = positionServices.findById(positionDto.getId());
        if (opPosition.isPresent()) {
            Position restorePosition = opPosition.get();
            restorePosition.setName(positionDto.getName());
            positionServices.save(restorePosition);
            positionServices.restorePosition(restorePosition.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw new DataInputException("Chức vụ không tồn tại");
        }
    }
}
