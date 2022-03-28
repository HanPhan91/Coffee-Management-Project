package com.cg.coffeemanagement.controller;

import com.cg.coffeemanagement.model.CatalogsMaterial;
import com.cg.coffeemanagement.model.Material;
import com.cg.coffeemanagement.services.Materials.IMaterialService;
import com.cg.coffeemanagement.services.catalogsMaterial.ICatalogsMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/materials")
public class MaterialController {

    @Autowired
    private ICatalogsMaterialService catalogsMaterialServiced;

    @Autowired
    private IMaterialService materialService;

    @GetMapping("/{catalogsMaterialId}")
    public ModelAndView showMaterials(@PathVariable Long catalogsMaterialId, Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView();
        Page<Material> materials = materialService.findByCatalogMaterialId(catalogsMaterialId,pageable);
        System.out.println(materials);
        modelAndView.setViewName("material/list");
        modelAndView.addObject("materials", materials);
        modelAndView.addObject("catalogsMaterialId", catalogsMaterialId);
        return modelAndView;
    }

//  //  @GetMapping("/materials")
//    public ModelAndView doMaterialListPage() {
//        ModelAndView modelAndView = new ModelAndView("catalogsMaterial/listMaterial");
//
//        return modelAndView;
//    }
}
