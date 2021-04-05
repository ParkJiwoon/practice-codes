package com.practice.springmessageconverterandlombok.controller;

import com.practice.springmessageconverterandlombok.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @PostMapping("/lombok")
    public ResponseEntity<LombokDto> postLombok(@RequestBody LombokDto exampleDto) {
        System.out.println("----- Request POST /lombok ------");
        System.out.println(exampleDto);
        return ResponseEntity.ok(exampleDto);
    }

    @PostMapping("/normal")
    public ResponseEntity<NormalDto> postNormal(@RequestBody NormalDto exampleDto) {
        System.out.println("----- Request POST /normal ------");
        System.out.println(exampleDto);
        return ResponseEntity.ok(exampleDto);
    }

    @PostMapping("/one")
    public ResponseEntity<OneDto> postOne(@RequestBody OneDto dto) {
        System.out.println("----- Request POST /one ------");
        System.out.println(dto);

        return ResponseEntity.ok(dto);
    }

    @PostMapping("/two")
    public ResponseEntity<TwoDto> postTwo(@RequestBody TwoDto dto) {
        System.out.println("----- Request POST /two ------");
        System.out.println(dto);

        return ResponseEntity.ok(dto);
    }
}
