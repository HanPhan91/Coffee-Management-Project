package com.cg.coffeemanagement.controller;

import com.cg.coffeemanagement.model.CatalogsMaterial;
import com.cg.coffeemanagement.model.Position;
import com.cg.coffeemanagement.services.Materials.IMaterialService;
import com.cg.coffeemanagement.services.catalogsMaterial.ICatalogsMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/catalogsMaterial")
public class CatalogsMaterialController {

    @Autowired
    private ICatalogsMaterialService catalogsMaterialServiced;

    @Autowired
    private IMaterialService materialService;

    @GetMapping
    public ModelAndView showCatalogsMaterial(){
        ModelAndView modelAndView = new ModelAndView();
        List<CatalogsMaterial> catalogsMaterials = catalogsMaterialServiced.findAll();
        modelAndView.setViewName("catalogsMaterial/list");
        modelAndView.addObject("catalogsMaterials", catalogsMaterials);
        return modelAndView;
    }

    @GetMapping("/materials")
    public ModelAndView doMaterialListPage() {
        ModelAndView modelAndView = new ModelAndView("catalogsMaterial/listMaterial");

        return modelAndView;
    }
}
