package com.cg.coffeemanagement.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "discounts")
public class Discount {

    @Id
    private Long id = System.currentTimeMillis();


    @NotNull(message = "Mã khuyến mãi không được để trống")
    @Size(min = 3, max = 50, message = "Mã khuyến mãi phải nằm trong khoảng 3-50 ký tự")
    @Column(unique = true)
    private String code;

    private String description;

    @Column(name = "percent_discount")
    private int percentDiscount;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Ho_Chi_Minh")
    @Column(name = "created_at")
    private Date createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Ho_Chi_Minh")
    @Column(name = "ended_at")
    private Date endedAt;

    @Column(name = "quantity")
    private int quantity;

    @Column(columnDefinition = "boolean default false")
    private boolean deleted;

    public Discount(String code, String description, int percentDiscount, Date createdAt, Date endedAt, int quantity) {
        this.code = code;
        this.description = description;
        this.percentDiscount = percentDiscount;
        this.createdAt = createdAt;
        this.endedAt = endedAt;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Discount{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", percentDiscount=" + percentDiscount +
                ", createdAt=" + createdAt +
                ", endedAt=" + endedAt +
                ", quantity=" + quantity +
                ", deleted=" + deleted +
                '}';
    }
}
