package com.example.springthymeleaf.upload.domain;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ItemForm {
    private Long itemId;
    private String itemName;
    private MultipartFile attachFile;
}
