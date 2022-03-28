package com.cg.coffeemanagement.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TempDTO {

    private String id;

    private String fileName;

    private String fileFolder;

    private String fileUrl;

    private String cloudId;

    private MultipartFile file;

}
