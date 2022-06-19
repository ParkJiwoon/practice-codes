package com.example.springthymeleaf.upload;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Component
public class ImageUploader {
    private static final String NFT_IMAGE_PREFIX = "/static/images/items/";

    @Value("${file.dir}")
    private String fileDir;

    public String upload(MultipartFile file) throws IOException {
        String imageUrl = wrapImageName(file.getOriginalFilename());
        String rootPath = getRootPath();
        file.transferTo(new File(rootPath + fileDir + imageUrl));
        return NFT_IMAGE_PREFIX + imageUrl;
    }

    private String wrapImageName(String imageName) {
        return UUID.randomUUID() + "-" + imageName;
    }

    private String getRootPath() {
        return Objects.requireNonNull(this.getClass().getResource(""))
                .getPath()
                .split("somsea")[0];
    }

}
