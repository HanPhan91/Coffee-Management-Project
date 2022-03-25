//package com.cg.coffeemanagement.controller.api;
//
//
//import com.cg.coffeemanagement.model.dto.TempDTO;
//import com.cg.coffeemanagement.services.Upload.TempService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/temp")
//public class TempRestControler {
//
//    @Autowired
//    private TempService tempService;
//
//    @PostMapping("/upload-image")
//    public ResponseEntity<?> upload(TempDTO tempDTO) {
//
//        Long randomId = System.currentTimeMillis() / 1000;
//
//
//        tempDTO.setId(randomId.toString());
//
//        tempService.uploadImage(tempDTO);
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//}
