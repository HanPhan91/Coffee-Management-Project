package com.cg.coffeemanagement.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "materials")
public class Material {
    @Id
    private Long id = System.currentTimeMillis() / 1000;

    @NotNull(message = "Tên nguyên liệu không được để trống")
    @Size(min = 5, max = 30, message = "Tên nguyên liệu phải nằm trong khoảng 5-30 ký tự")
    @Column(name = "material_name")
    private String name;

    @Digits(integer = 12, fraction = 0)
    private BigDecimal price;

    private BigDecimal quantity;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss Z", timezone = "Asia/Ho_Chi_Minh")
    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "id_catalogs_material")
    private CatalogsMaterial catalogsMaterial;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @Override
    public String toString() {
        return "Material{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", createdAt=" + createdAt +
                ", catalogsMaterial=" + catalogsMaterial +
                ", user=" + user +
                '}';
    }
}
