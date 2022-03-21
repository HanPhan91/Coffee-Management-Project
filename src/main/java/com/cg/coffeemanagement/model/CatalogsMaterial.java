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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "catalogs_materials")
public class CatalogsMaterial {
    @Id
    private Long id = System.currentTimeMillis()/1000;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss Z", timezone = "Asia/Ho_Chi_Minh")
    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    @NotNull(message = "Tên danh mục nguyên liệu không được để trống")
    @Size(min = 5, max = 30, message = "Tên danh mục nguyên liệu phải nằm trong khoảng 5-30 ký tự")
    @Column(name = "catalog_material_name")
    private String name;

    @Column(columnDefinition = "boolean default false")
    private boolean deleted;
}
