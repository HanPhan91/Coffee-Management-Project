package com.cg.coffeemanagement.model.dto;


import com.fasterxml.jackson.annotation.JsonFormat;

import com.cg.coffeemanagement.model.Position;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StaffDto {

    private Long id;

    @NotNull(message = "Tên nhân viên không được để trống")
    @Size(min = 5, max = 30, message = "Tên nhân viên phải có độ dài nằm trong khoảng 5-30 ký tự")
    private String name;

    @NotNull(message = "Địa chỉ nhân viên không được để trống")
    @Size(min = 3, message = "Địa chỉ phải có ít nhất 3 ký tự")
    private String address;

    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Số điện thoại không đúng")
    private String phone;

    private String email;

    @NotNull(message = "Ngày sinh không được để trống")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Ho_Chi_Minh")
    private Date birthDay;

    private Long position;
}
