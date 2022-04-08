package com.cg.coffeemanagement.controller;

import com.cg.coffeemanagement.Static.Principal;
import com.cg.coffeemanagement.model.Catalog;
import com.cg.coffeemanagement.model.User;
import com.cg.coffeemanagement.services.Catalog.CatalogService;
import com.cg.coffeemanagement.services.Users.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/catalogs")
public class CatalogController {

    @Autowired
    private CatalogService catalogService;
    @Autowired
    private IUserService userServices;

    @GetMapping
    public ModelAndView listCatalogs(){
        ModelAndView modelAndView = new ModelAndView();
        List<Catalog> catalogs = catalogService.findAllNotDeleted();
        modelAndView.setViewName("catalog/list");
        User user = userServices.getByUsername(Principal.getPrincipal()).get();
        modelAndView.addObject("user", user);
        modelAndView.addObject("catalogs",catalogs);
        return modelAndView;
    }

    @GetMapping("/deleted")
    public ModelAndView listCatalogsDeleted(){
        ModelAndView modelAndView = new ModelAndView();
        List<Catalog> catalogs = catalogService.findAllDeleted();
        modelAndView.setViewName("catalog/listDeleted");
        User user = userServices.getByUsername(Principal.getPrincipal()).get();
        modelAndView.addObject("user", user);
        modelAndView.addObject("catalogs",catalogs);
        return modelAndView;
    }


}
