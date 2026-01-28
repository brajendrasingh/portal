package com.bksoft.auth.api.controller;

import com.bksoft.auth.services.AzureBlobService;
import com.bksoft.auth.services.FileUploadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/file")
public class FileUploadController {

    private final AzureBlobService blobService;
    private final FileUploadService fileUploadService;

    public FileUploadController(FileUploadService fileUploadService, AzureBlobService blobService) {
        this.fileUploadService = fileUploadService;
        this.blobService = blobService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam MultipartFile file) throws IOException {
        return ResponseEntity.ok(fileUploadService.uploadFile(file));
    }

    @PostMapping("/azure-blob/upload")
    public ResponseEntity<String> uploadFileToAzureBlob(@RequestParam MultipartFile file) throws IOException {
        return ResponseEntity.ok(blobService.uploadFile(file));
    }
}
