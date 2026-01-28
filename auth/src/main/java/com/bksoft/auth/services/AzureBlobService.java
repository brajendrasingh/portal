package com.bksoft.auth.services;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AzureBlobService {

    private final BlobContainerClient containerClient;

    public String uploadFile(MultipartFile file) throws IOException {
        BlobClient blobClient = containerClient.getBlobClient(file.getOriginalFilename());
        blobClient.upload(file.getInputStream(), file.getSize(), true);
        return blobClient.getBlobUrl();
    }

    public byte[] downloadFile(String fileName) {
        BlobClient blobClient = containerClient.getBlobClient(fileName);
        return blobClient.downloadContent().toBytes();
    }

    public void deleteFile(String fileName) {
        containerClient.getBlobClient(fileName).delete();
    }
}
