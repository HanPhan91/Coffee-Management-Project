package com.cg.coffeemanagement.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "drinks")
public class Drink {
    @Id
    private Long id = System.currentTimeMillis()/1000;

    @NotNull(message = "Tên thức uống là bắt buộc")
    @Size(min = 5, max = 50, message = "Tên thức uống phải nằm trong khoảng 5-50 ký tự")
    @Column(name = "drink_name")
    private String name;


    @Digits(integer = 12, fraction = 0)
//    @Column(updatable = false)
    @NotNull(message = "Giá thức uống là bắt buộc")
    @DecimalMin(value = "500", message = "Giá thức uống phải từ 500d đến 100.000d")
    @DecimalMax(value = "100000", message = "Giá thức uống phải từ 500d đến 100.000d")
    private BigDecimal price;

    private String description;

    @Column(columnDefinition = "boolean default false")
    private boolean deleted;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss Z", timezone = "Asia/Ho_Chi_Minh")
    @Column(name = "created_at", updatable = false)
    private Date createdAt;


    private String imgUrl;


}
