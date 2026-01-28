package com.bksoft.auth.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileUploadService {

    public String uploadFile(MultipartFile file) throws IOException {
        return "success";
    }

    public byte[] downloadFile(String fileName) {
        return fileName.getBytes();
    }

    public void deleteFile(String fileName) {

    }
}
