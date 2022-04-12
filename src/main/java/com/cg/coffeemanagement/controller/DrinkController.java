package com.cg.coffeemanagement.controller;//package com.cg.coffeemanagement.controller;

import com.cg.coffeemanagement.Static.Principal;
import com.cg.coffeemanagement.model.Catalog;
import com.cg.coffeemanagement.model.Drink;
import com.cg.coffeemanagement.model.User;
import com.cg.coffeemanagement.services.Catalog.CatalogService;
import com.cg.coffeemanagement.services.Drink.DrinkService;
import com.cg.coffeemanagement.services.Users.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/drinks")
public class DrinkController {
    @Autowired
    private DrinkService drinkService;
    @Autowired
    private CatalogService catalogService;
    @Autowired
    private IUserService userServices;

    @GetMapping
    public ModelAndView showListPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("drink/list");
        List<Drink> drinks = drinkService.findAllNotDeleted();
        List<Catalog> catalogDrinks = catalogService.findAllNotDeleted();
        User user = userServices.getByUsername(Principal.getPrincipal()).get();
        modelAndView.addObject("user", user);
        modelAndView.addObject("catalogs", catalogDrinks);
        modelAndView.addObject("drinks", drinks);
        return modelAndView;
    }

    @GetMapping("/{idCatalog}")
    public ModelAndView getDrinkByCatalog(@PathVariable Long idCatalog){
        ModelAndView modelAndView = new ModelAndView();
        List<Catalog> catalogDrinks = catalogService.findAllNotDeleted();
        modelAndView.setViewName("drink/list");
        User user = userServices.getByUsername(Principal.getPrincipal()).get();
        modelAndView.addObject("user", user);
        modelAndView.addObject("catalogs", catalogDrinks);
        Optional<Catalog> opCatalog = catalogService.findById(idCatalog);
        if (opCatalog.isPresent()){
            Catalog catalog = opCatalog.get();
            List<Drink> drinks = drinkService.findAllByCatalogAndDeletedFalse(catalog);
            modelAndView.addObject("drinks", drinks);
            return modelAndView;
        }
        else {
            modelAndView.addObject("drinks", null);
            return modelAndView;
        }
    }


    @GetMapping("/deleted")
    public ModelAndView showListDeleted() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("drink/deleted");
        List<Drink> drinks = drinkService.findAllDeleted();
        List<Catalog> catalogDrinks = catalogService.findAllNotDeleted();
        User user = userServices.getByUsername(Principal.getPrincipal()).get();
        modelAndView.addObject("user", user);
        modelAndView.addObject("catalogs", catalogDrinks);
        modelAndView.addObject("drinks", drinks);
        return modelAndView;
    }
}
