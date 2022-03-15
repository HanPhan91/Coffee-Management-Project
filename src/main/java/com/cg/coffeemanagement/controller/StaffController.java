package com.cg.coffeemanagement.controller;

import com.cg.coffeemanagement.model.Staff;
import com.cg.coffeemanagement.services.Staffs.IStaffServices;
import com.cg.coffeemanagement.services.Staffs.StaffServicesImpl;
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

    @GetMapping
    public ModelAndView showStaff(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("staffs/list");
        List<Staff> staffs = staffServices.findByDeletedFalse();
        modelAndView.addObject("staffs", staffs);
        return modelAndView;
    }
}
