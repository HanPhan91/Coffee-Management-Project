package com.cg.coffeemanagement.controller;

import com.cg.coffeemanagement.Static.Principal;
import com.cg.coffeemanagement.model.Staff;
import com.cg.coffeemanagement.model.User;
import com.cg.coffeemanagement.services.Staffs.IStaffServices;
import com.cg.coffeemanagement.services.Users.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/staffs")
public class StaffController {

    @Autowired
    private IStaffServices staffServices;

    @Autowired
    private IUserService userServices;

    @GetMapping
    public ModelAndView showStaff(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("staffs/list");
        User user = userServices.getByUsername(Principal.getPrincipal()).get();
        List<Staff> staffs = staffServices.findStaffNotDeletedAndPermissionSmaller(user.getStaff().getPosition().getPermission().getPermissionAccess());
        modelAndView.addObject("user", user);
        modelAndView.addObject("staffs", staffs);
        return modelAndView;
    }

    @GetMapping("/deleted")
    public ModelAndView showStaffDeleted(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("staffs/deleted");
        User user = userServices.getByUsername(Principal.getPrincipal()).get();
        List<Staff> staffs = staffServices.findStaffNotDeletedAndPermissionSmaller(user.getStaff().getPosition().getPermission().getPermissionAccess());
        modelAndView.addObject("user", user);
        modelAndView.addObject("staffs", staffs);
        return modelAndView;
    }


}
