package com.cg.coffeemanagement.model.dto;

import com.cg.coffeemanagement.model.Discount;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DiscountDTO {

    private Long id;

    @NotNull(message = "Mã khuyến mãi không được để trống")
    @Size(min = 3, max = 50, message = "Mã khuyến mãi phải nằm trong khoảng 3-50 ký tự")
    private String code;

    private String description;

    private int percentDiscount;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Ho_Chi_Minh")
    private Date createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Ho_Chi_Minh")
    private Date endedAt;

    private int quantity;

    public Discount toDiscount() {
        return new Discount(code, description, percentDiscount, createdAt, endedAt, quantity);
    }

    public static DiscountDTO parse(Discount discount) {
        return new DiscountDTO(discount.getId(),discount.getCode(), discount.getDescription(), discount.getPercentDiscount(), discount.getCreatedAt(), discount.getEndedAt(), discount.getQuantity());
    }
}
