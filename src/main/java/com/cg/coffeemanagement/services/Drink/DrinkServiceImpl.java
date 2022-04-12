package com.cg.coffeemanagement.services.Drink;


import com.cg.coffeemanagement.exception.DataInputException;
import com.cg.coffeemanagement.model.*;
import com.cg.coffeemanagement.model.dto.DrinkDto;
import com.cg.coffeemanagement.repository.Drink.DrinkRepository;
import com.cg.coffeemanagement.services.Catalog.CatalogService;
import com.cg.coffeemanagement.services.Upload.UploadService;
import com.cg.coffeemanagement.utils.UploadUtils;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class DrinkServiceImpl implements DrinkService{
    public static final String IMAGE_UPLOAD_FOLDER = "drink";

    @Autowired
    CatalogService catalogService;

    @Autowired
    UploadService uploadService;

    @Autowired
    DrinkRepository drinkRepository;

    @Override
    public void deleteDrinkById(Long id) {
            drinkRepository.deleteDrinkById(id);
    }

    @Override
    public void restoreDrink(Long id) {
        drinkRepository.restoreDrink(id);

    }

    @Override
    public List<Drink> findAllNotDeleted() {
        return drinkRepository.findAllNotDeleted();
    }

    @Override
    public List<Drink> findAllDeleted() {
        return drinkRepository.findAllDeleted();
    }

    @Override
    public Integer countDrink() {
        return drinkRepository.countDrink();
    }

    @Override
    public List<Drink> findAll() {
        return drinkRepository.findAll();
    }

    @Override
    public Optional<Drink> findById(Long id) {
        return drinkRepository.findById(id);
    }

    @Override
    public Drink getById(Long id) {
        return drinkRepository.getById(id);
    }

    @Override
    public Drink save(Drink drink) {
        return drinkRepository.save(drink);
    }

    @Override
    public void remove(Long id) {
        drinkRepository.deleteById(id);
    }

    @Override
    public boolean existsByName(String name) {
        return drinkRepository.existsByName(name);
    }

    @Override
    public Drink create(DrinkDto drinkDto) {
        Drink drink = drinkDto.toDrink();
        if (drinkDto.getFile() == null) {
            drink.setCatalog(catalogService.findById(drinkDto.getCatalog()).get());
            save(drink);
            return drink;
        }
        drink.setCatalog(catalogService.findById(drinkDto.getCatalog()).get());
        try {
            Long nameImg = System.currentTimeMillis() / 1000;
            String publicId = String.format("%s/%s", IMAGE_UPLOAD_FOLDER, nameImg);
            Map uploadResult = uploadService.uploadImage(drinkDto.getFile(), ObjectUtils.asMap(
                    "public_id", publicId,
                    "overwrite", true,
                    "resource_type", "image"
            ));
            String fileUrl = (String) uploadResult.get("secure_url");
            drink.setImgUrl(fileUrl);
            save(drink);
        } catch (IOException e) {
            e.printStackTrace();
            throw new DataInputException("Upload thất bại");
        }
        return drink;
    }

    @Override
    public Drink update(Drink drink, DrinkDto drinkDto) {
        drink.setName(drinkDto.getName());
        drink.setPrice(drinkDto.getPrice());
        drink.setCatalog(catalogService.findById(drinkDto.getCatalog()).get());
        drink.setDescription(drinkDto.getDescription());
        if (drinkDto.getFile() == null) {
            save(drink);
            return drink;
        }
        try {
            Long nameImg = System.currentTimeMillis() / 1000;
            String publicId = String.format("%s/%s", IMAGE_UPLOAD_FOLDER, nameImg);
            Map uploadResult = uploadService.uploadImage(drinkDto.getFile(), ObjectUtils.asMap(
                    "public_id", publicId,
                    "overwrite", true,
                    "resource_type", "image"
            ));
            String fileUrl = (String) uploadResult.get("secure_url");
            drink.setImgUrl(fileUrl);
            save(drink);
        } catch (IOException e) {
            e.printStackTrace();
            throw new DataInputException("Upload thất bại");
        }
        return drink;
    }

    @Override
    public List<Drink> findAllByCatalog(Catalog catalog) {
        return drinkRepository.findAllByCatalog(catalog);
    }

    @Override
    public void deleteDrinkByCatalog(Long idCatalog) {
        drinkRepository.deleteDrinkByCatalog(idCatalog);
    }

    @Override
    public Integer countDrinkByCatalog(Catalog catalog) {
        return drinkRepository.countDrinkByCatalog(catalog);
    }

    @Override
    public List<Drink> findAllByCatalogAndDeletedFalse(Catalog catalog) {
        return drinkRepository.findAllByCatalogAndDeletedFalse(catalog);
    }
}
