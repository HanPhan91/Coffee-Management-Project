package com.cg.coffeemanagement.controller;

import com.cg.coffeemanagement.model.Permission;
import com.cg.coffeemanagement.model.Position;
import com.cg.coffeemanagement.services.Permission.IPermissionServices;
import com.cg.coffeemanagement.services.Positions.IPositionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/positions")
public class PositionController {

    @Autowired
    private IPositionServices positionServices;


    @GetMapping
    public ModelAndView showPosition(){
        ModelAndView modelAndView = new ModelAndView();
        List<Position> positions = positionServices.findByDeletedFalse();
        modelAndView.setViewName("positions/list");
        modelAndView.addObject("positions", positions);
        return modelAndView;
    }

    @GetMapping("/deleted")
    public ModelAndView showPositionDeleted(){
        ModelAndView modelAndView = new ModelAndView();
        List<Position> positions = positionServices.findByDeletedTrue();
        modelAndView.setViewName("positions/deleted");
        modelAndView.addObject("positions", positions);
        return modelAndView;
    }
}
