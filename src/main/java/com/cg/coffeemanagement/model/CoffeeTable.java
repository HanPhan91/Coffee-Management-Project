package com.cg.coffeemanagement.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tables")
public class CoffeeTable {

    @Id
    private Long id = System.currentTimeMillis()/1000;

    @NotNull(message = "Tên bàn là bắt buộc")
    @Size(min = 5, max = 50, message = "Tên thức uống phải nằm trong khoảng 5-50 ký tự")
    @Column(name = "drink_name")
    private String name;

    private String description;

    private boolean deleted;



}
