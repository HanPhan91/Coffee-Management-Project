package com.cg.coffeemanagement.model.dto;

import com.cg.coffeemanagement.model.Staff;
import com.cg.coffeemanagement.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.web.multipart.MultipartFile;
import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {

    private Long id = System.currentTimeMillis() / 1000;

    private String username;

    private String password;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss Z", timezone = "Asia/Ho_Chi_Minh")
    private Date createAt;

    private boolean deleted = false;

    private String avatarUrl;

    private Long staff;

    private MultipartFile file;

    public User toUser(){
        return new User()
                .setUsername(username)
                .setPassword(password)
                .setCreateAt(createAt)
                .setAvatarUrl(avatarUrl);
    }
}
