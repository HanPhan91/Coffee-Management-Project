package com.cg.coffeemanagement.controller.api;

import com.cg.coffeemanagement.Static.Principal;
import com.cg.coffeemanagement.exception.DataInputException;
import com.cg.coffeemanagement.model.Position;
import com.cg.coffeemanagement.model.Staff;
import com.cg.coffeemanagement.model.User;
import com.cg.coffeemanagement.model.dto.StaffDto;
import com.cg.coffeemanagement.services.Positions.IPositionServices;
import com.cg.coffeemanagement.services.Staffs.IStaffServices;
import com.cg.coffeemanagement.services.Users.IUserService;
import com.cg.coffeemanagement.utils.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/staffs")
public class StaffApi {

    @Autowired
    private IStaffServices staffServices;
    @Autowired
    private IPositionServices positionServices;
    @Autowired
    private AppUtil appUtil;
    @Autowired
    private IUserService userService;

    @GetMapping
    public ResponseEntity<?> getStaffActive(){
        List<Staff> staffs = staffServices.findByDeletedFalse();
        return new ResponseEntity<>(staffs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStaff(@PathVariable Long id) {
        Optional<Staff> staff = staffServices.findById(id);
        if (staff.isPresent()) {
            return new ResponseEntity<>(staff.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> doCreate(@Validated @RequestBody StaffDto staffDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return appUtil.mapErrorToResponse(bindingResult);
        }
        Staff staff = new Staff();
        staff.setName(staffDto.getName());
        staff.setAddress(staffDto.getAddress());
        staff.setPhone(staffDto.getPhone());
        staff.setEmail(staffDto.getEmail());
        staff.setBirthDay(staffDto.getBirthDay());
        staff.setPosition(positionServices.findById(staffDto.getPosition()).get());
        Staff returnStaff = staffServices.save(staff);
        return new ResponseEntity<>(returnStaff, HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> doEdit(@PathVariable Long id, @Validated @RequestBody StaffDto staffDto,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return appUtil.mapErrorToResponse(bindingResult);
        }
        Optional<Staff> opStaff = staffServices.findById(id);
        if (opStaff.isPresent()) {
            Staff staff = opStaff.get();
            staff.setName(staffDto.getName());
            staff.setAddress(staffDto.getAddress());
            staff.setPhone(staffDto.getPhone());
            staff.setEmail(staffDto.getEmail());
            staff.setBirthDay(staffDto.getBirthDay());
            staff.setPosition(positionServices.findById(staffDto.getPosition()).get());
            Staff returnStaff = staffServices.save(staff);
            return new ResponseEntity<>(returnStaff, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<?> doDelete(@PathVariable Long id, @Validated @RequestBody StaffDto staffDto) {
        Optional<Staff> opStaff = staffServices.findById(id);
        if (opStaff.isPresent()) {
            User presentUser = userService.getByUsername(Principal.getPrincipal()).get();
            if (presentUser.getStaff().getPosition().getPermission().getPermissionAccess() >= opStaff.get().getPosition().getPermission().getPermissionAccess()){
                throw new DataInputException("Không đủ quyền để thực hiện thao tác này");
            }
            staffServices.deleteStaff(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw new DataInputException("Nhân viên không tồn tại");
        }
    }

    @PutMapping("/restore/{id}")
    public ResponseEntity<?> doRestore(@PathVariable Long id, @Validated @RequestBody StaffDto staffDto,
                                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return appUtil.mapErrorToResponse(bindingResult);
        }
        Optional<Staff> opStaff = staffServices.findById(id);
        if (opStaff.isPresent()) {
            User presentUser = userService.getByUsername(Principal.getPrincipal()).get();
            if (presentUser.getStaff().getPosition().getPermission().getPermissionAccess() >= opStaff.get().getPosition().getPermission().getPermissionAccess()){
                throw new DataInputException("Không đủ quyền để thực hiện thao tác này");
            }
            Staff staff = opStaff.get();
            staff.setName(staffDto.getName());
            staff.setAddress(staffDto.getAddress());
            staff.setPhone(staffDto.getPhone());
            staff.setEmail(staffDto.getEmail());
            staff.setBirthDay(staffDto.getBirthDay());
            staff.setPosition(positionServices.findById(staffDto.getPosition()).get());
            staffServices.save(staff);
            staffServices.restoreStaff(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
