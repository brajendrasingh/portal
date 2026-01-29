package com.bksoft.auth.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileUploadService {

    private final Path fileStorageLocation;

    public FileUploadService(@Value("${file.upload-dir}") String uploadDir) {
        if (uploadDir == null || uploadDir.isBlank()) {
            throw new IllegalStateException("file.upload-dir is not configured");
        }
        this.fileStorageLocation = Paths.get(uploadDir).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("Could not create upload directory", ex);
        }
    }

    public String uploadFile(MultipartFile file) {
        String fileName = Path.of(file.getOriginalFilename()).getFileName().toString();
        try {
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (IOException ex) {
            throw new RuntimeException("Could not store file " + fileName, ex);
        }
    }

    public byte[] downloadFile(String fileName) {
        return fileName.getBytes();
    }

    public void deleteFile(String fileName) {

    }
}
