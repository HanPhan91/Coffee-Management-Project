package com.cg.coffeemanagement.model;

import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Columns;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "avatars")
public class Avatar {

    @Id
    private Long id = System.currentTimeMillis() / 1000;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_foler")
    private String fileFolder;

    @Column(name = "file_url")
    private String fileUrl;

    @Column(name = "cloud_id")
    private String cloudId;

}
