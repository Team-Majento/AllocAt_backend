package com.uom.seat.server.rest.controller;


import com.uom.seat.ImageUpload.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/file")
public class UploadFileController {

    @Autowired
    private FileService fileService;

    @PostMapping()
    public ResponseEntity<Map<String,String>> uploadFile(@RequestParam("file")MultipartFile file){
        String publicUrl= fileService.uploadFile(file);
        Map<String,String> response = new HashMap<>();
        response.put("publicURL",publicUrl);
        return new ResponseEntity<Map<String,String>>(response, HttpStatus.CREATED);
    }

}
