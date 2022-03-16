package com.cg.coffeemanagement.controller;

import com.cg.coffeemanagement.model.Catalog;
import com.cg.coffeemanagement.services.Catalog.CatalogService;
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
    CatalogService catalogService;

    @GetMapping
    public ModelAndView listCatalogs(){
        ModelAndView modelAndView = new ModelAndView();
        List<Catalog> catalogs = catalogService.findAll();
        modelAndView.setViewName("catalog/list");
        modelAndView.addObject("catalogs",catalogs);
        return modelAndView;
    }

    @GetMapping("/deleted")
    public ModelAndView listCatalogsDeleted(){
        ModelAndView modelAndView = new ModelAndView();
        List<Catalog> catalogs = catalogService.findAllDeleted();
        modelAndView.setViewName("catalog/listDeleted");
        modelAndView.addObject("catalogs",catalogs);
        return modelAndView;
    }


}
