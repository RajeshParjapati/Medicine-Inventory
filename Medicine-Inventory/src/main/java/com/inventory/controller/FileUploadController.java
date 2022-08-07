package com.inventory.controller;

import com.inventory.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/upload")
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;

    @PostMapping("/csv")
    public ResponseEntity fileUpload(@RequestParam("file") MultipartFile file) {
        fileUploadService.uploadFile(file);
        return ResponseEntity.ok("File Uploaded successfully");
    }

}
