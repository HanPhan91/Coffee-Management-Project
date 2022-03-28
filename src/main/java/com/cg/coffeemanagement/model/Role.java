//package com.cg.coffeemanagement.model;
//
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import org.hibernate.annotations.CreationTimestamp;
//import javax.persistence.*;
//import java.util.Date;
//
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
//@Entity
//@Table(name = "roles")
//public class Role {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String name;
//
//    @CreationTimestamp
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss Z", timezone = "Asia/Ho_Chi_Minh")
//    @Column(name = "create_at", updatable = false)
//    private Date createAt;
//
//    @Column(columnDefinition = "boolean default false")
//    private boolean deleted;
//
//    @OneToOne
//    @JoinColumn(name = "id_permission")
//    private Permission permission;
//
//}
