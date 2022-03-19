package com.cg.coffeemanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.web.multipart.MultipartFile;
import javax.persistence.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Avatar{

    @Id
    private Long id = System.currentTimeMillis()/1000;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_foler")
    private String fileFolder;

    @Column(name = "file_url")
    private String fileUrl;

    @Column(name = "cloud_id")
    private String cloudId;

}
