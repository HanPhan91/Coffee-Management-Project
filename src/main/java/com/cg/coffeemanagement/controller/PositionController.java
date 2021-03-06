package com.cg.coffeemanagement.controller;

import com.cg.coffeemanagement.Static.Principal;
import com.cg.coffeemanagement.model.Position;
import com.cg.coffeemanagement.model.User;
import com.cg.coffeemanagement.services.Positions.IPositionServices;
import com.cg.coffeemanagement.services.Users.IUserService;
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

    @Autowired
    private IUserService userServices;

    @GetMapping
    public ModelAndView showPosition(){
        ModelAndView modelAndView = new ModelAndView();
        List<Position> positions = positionServices.findByDeletedFalse();
        User user = userServices.getByUsername(Principal.getPrincipal()).get();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("positions/list");
        modelAndView.addObject("positions", positions);

        return modelAndView;
    }

    @GetMapping("/deleted")
    public ModelAndView showPositionDeleted(){
        ModelAndView modelAndView = new ModelAndView();
        List<Position> positions = positionServices.findByDeletedTrue();
        User user = userServices.getByUsername(Principal.getPrincipal()).get();
        modelAndView.setViewName("positions/deleted");
        modelAndView.addObject("positions", positions);
        modelAndView.addObject("user", user);
        return modelAndView;
    }

//    @GetMapping("/deleted")
//    public ModelAndView showPositionDeleted(){
//        ModelAndView modelAndView = new ModelAndView();
//        List<Position> positions = positionServices.findByDeletedTrue();
//        modelAndView.setViewName("positions/deleted");
//        modelAndView.addObject("positions", positions);
//        return modelAndView;
//    }
}
