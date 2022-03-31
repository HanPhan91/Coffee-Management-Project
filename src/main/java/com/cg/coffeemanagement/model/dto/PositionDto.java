package com.cg.coffeemanagement.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PositionDto {
    private Long id;

    @NotNull(message = "Tên chức vụ không được để trống")
    @Size(min = 5, max = 30, message = "Tên chức vụ phải nằm trong khoảng 5-30 ký tự")
    private String name;

    @NotNull(message = "Nhiệm vụ không được để trống")
    private Long permission;
}
