package com.example.springthymeleaf.upload.domain;

import lombok.Data;

/**
 * 고객이 업로드한 파일명을 서버 내부에 그대로 저장하면 파일 이름 중복으로 충돌될 수 있음
 * 서버에서는 저장할 파일명과 겹치지 않도록 내부에서 관리하는 별도의 파일명이 필요하다
 */
@Data
public class UploadFile {
    // 고객이 업로드한 파일명
    private String uploadFileName;

    // 서버 내부에서 관리하는 파일명
    private String storeFileName;

    public UploadFile(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }
}
