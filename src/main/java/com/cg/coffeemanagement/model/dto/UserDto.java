package com.cg.coffeemanagement.model.dto;

import com.cg.coffeemanagement.model.Avatar;
import com.cg.coffeemanagement.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.web.multipart.MultipartFile;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserDto {

    private Long id = System.currentTimeMillis() / 1000;

    @NotBlank(message = "Tên đăng nhập không được để trống")
    @Size(min = 5, max = 30, message = "Tên đăng nhập phải nằm trong khoảng 5-30 ký tự")
    private String username;

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

    public UserDto(Long id, String username) {
        this.id = id;
        this.username = username;
    }

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
