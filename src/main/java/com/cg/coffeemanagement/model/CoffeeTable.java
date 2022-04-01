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
    @Size(min = 1, max = 50, message = "Tên bàn phải nằm trong khoảng 1-50 ký tự")
    @Column(name = "name")
    private String name;

    @Column(columnDefinition = "boolean default false")
    private boolean deleted;

}
