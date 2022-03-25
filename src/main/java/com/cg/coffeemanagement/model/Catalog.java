package com.cg.coffeemanagement.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "catalogs")
public class Catalog {
    @Id
    private Long id = System.currentTimeMillis()/1000;

    @NotNull(message = "Tên danh mục thức uống không được để trống")
    @Size(min = 3, max = 50, message = "Tên danh mục thức uống phải nằm trong khoảng 3-50 ký tự")
    @Column(name = "name_catalog")
    private String catalogName;

    @Column(columnDefinition = "boolean default false")
    private boolean deleted;

    private String description;


}
