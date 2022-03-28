package com.cg.coffeemanagement.model.dto;

import com.cg.coffeemanagement.model.Avatar;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class AvatarDto {

    private Long id = (System.currentTimeMillis() / 1000);

    private String fileName;

    private String fileFolder;

    private String fileUrl;

    private String cloudId;

    @NotNull(message = "Vui lòng chọn ảnh đại diện")
    private MultipartFile file;

    public Avatar toAvatar() {
        return new Avatar()
                .setId(id)
                .setFileName(fileName)
                .setFileFolder(fileFolder)
                .setFileUrl(fileUrl)
                .setCloudId(cloudId);
    }
}
