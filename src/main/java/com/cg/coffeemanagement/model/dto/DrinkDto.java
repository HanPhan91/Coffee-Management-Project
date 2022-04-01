package com.cg.coffeemanagement.model.dto;


import com.cg.coffeemanagement.model.Drink;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DrinkDto {

    private Long id= System.currentTimeMillis() / 1000;

    @NotNull(message = "Tên thức uống là bắt buộc")
    @Size(min = 5, max = 50, message = "Tên thức uống phải nằm trong khoảng 5-50 ký tự")
    private String name;

    @NotNull(message = "Giá thức uống là bắt buộc")
    @DecimalMin(value = "500", message = "Giá thức uống phải từ 500d đến 100.000d")
    @DecimalMax(value = "100000", message = "Giá thức uống phải từ 500d đến 100.000d")
    private BigDecimal price;

    private Long catalog;

    private MultipartFile file;

    private String description;

    public Drink toDrink(){
        return new Drink()
                .setId(id)
                .setName(name)
                .setPrice(price)
                .setDescription(description);
    }
}
