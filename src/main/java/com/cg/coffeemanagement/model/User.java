package com.cg.coffeemanagement.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Accessors(chain = true)
@Table(name = "users")
public class User {

    @Id
    private Long id = System.currentTimeMillis() / 1000;


    @NotNull(message = "Tên đăng nhập không được để trống")
    @Size(min = 5, max = 30, message = "Tên đăng nhập phải nằm trong khoảng 5-30 ký tự")
    private String username;

    @NotNull(message = "Mật khẩu không được để trống")
    @Size(min = 5, message = "Mật khẩu phải có ít nhất 5 ký tự")
    private String password;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss Z", timezone = "Asia/Ho_Chi_Minh")
    @Column(name = "create_at", updatable = false)
    private Date createAt;

    @Column(columnDefinition = "boolean default false")
    private boolean deleted;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @OneToOne
    @JoinColumn(name = "id_staff")
    private Staff staff;

    @OneToOne
    @JoinColumn(name = "id_avatar")
    private Avatar avatar;
}
