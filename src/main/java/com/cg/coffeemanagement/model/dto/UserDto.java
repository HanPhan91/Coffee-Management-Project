package com.cg.coffeemanagement.model.dto;

import com.cg.coffeemanagement.model.Avatar;
import com.cg.coffeemanagement.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
<<<<<<< HEAD
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.web.multipart.MultipartFile;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
=======
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.web.multipart.MultipartFile;
import javax.persistence.*;
>>>>>>> han
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

<<<<<<< HEAD
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
=======
@Data
>>>>>>> han
public class UserDto {

    private Long id = System.currentTimeMillis() / 1000;

<<<<<<< HEAD
    private String username;

=======
    @NotNull(message = "Tên đăng nhập không được để trống")
    @Size(min = 5, max = 30, message = "Tên đăng nhập phải nằm trong khoảng 5-30 ký tự")
    private String username;

    @NotNull(message = "Mật khẩu không được để trống")
    @Size(min = 5, message = "Mật khẩu phải có ít nhất 5 ký tự")
>>>>>>> han
    private String password;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss Z", timezone = "Asia/Ho_Chi_Minh")
    private Date createAt;

    private String fileName;

    private String fileFolder;

    private String fileUrl;

    private String cloudId;

    private Long id_staff;

    private MultipartFile file;

<<<<<<< HEAD
    public UserDto(Long id, String username) {
        this.id = id;
        this.username = username;
    }

=======
>>>>>>> han
    public Avatar toAvatar() {
        return new Avatar()
                .setId(id)
                .setFileName(fileName)
                .setFileFolder(fileFolder)
                .setFileUrl(fileUrl)
                .setCloudId(cloudId);
    }

    public User toUser(){
        return new User()
                .setId(id)
                .setUsername(username)
                .setPassword(password);
    }
}
