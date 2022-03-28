package com.cg.coffeemanagement.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
<<<<<<< HEAD
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
=======
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
>>>>>>> han
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

<<<<<<< HEAD
    @NotNull(message = "Tên đăng nhập không được để trống")
    @Size(min = 5, max = 30, message = "Tên đăng nhập phải nằm trong khoảng 5-30 ký tự")
=======
    @NotBlank(message = "Tên đăng nhập không được để trống")
    @Size(min = 5, max = 30, message = "Tên đăng nhập phải có độ dài trong khoảng 5-30 ký tự")
    @Column(unique = true)
>>>>>>> 76ef7e28c0e0cbb24d8107ed6a49cf883121b8b9
    private String username;

    @NotBlank(message = "Mật khẩu không được để trống")
    @Size(min = 6, max = 30, message = "Mật khẩu phải lớn hơn 6 ký tự")
    @Column(updatable = false)
    private String password;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss Z", timezone = "Asia/Ho_Chi_Minh")
    @Column(name = "create_at", updatable = false)
    private Date createAt;

    @Column(columnDefinition = "boolean default false")
    private boolean deleted;

    @OneToOne
    @JoinColumn(name = "id_staff")
    private Staff staff;

    @OneToOne
    @JoinColumn(name = "id_avatar")
    private Avatar avatar;
}
