package com.bakery_shop.service;
import org.springframework.stereotype.Service;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class CloudinaryService {

    private final Cloudinary cloudinary;

    public CloudinaryService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    // Upload 1 ảnh
    public String uploadFile(MultipartFile file) {
        try {
            Map result = cloudinary.uploader().upload(
                    file.getBytes(),
                    ObjectUtils.emptyMap()
            );

            return result.get("secure_url").toString();

        } catch (Exception e) {
            throw new RuntimeException("Upload failed");
        }
    }

    // Upload nhiều ảnh
    public List<String> uploadFiles(MultipartFile[] files) {

        List<String> urls = new ArrayList<>();

        for (MultipartFile file : files) {
            urls.add(uploadFile(file));
        }

        return urls;
    }

    // Delete ảnh
    public String deleteFile(String publicId) {

        try {

            Map result = cloudinary.uploader().destroy(
                    publicId,
                    ObjectUtils.emptyMap()
            );

            return result.get("result").toString();

        } catch (Exception e) {
            throw new RuntimeException("Delete failed");
        }
    }
}