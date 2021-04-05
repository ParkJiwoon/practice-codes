package com.practice.springmessageconverterandlombok.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.springmessageconverterandlombok.dto.LombokDto;
import com.practice.springmessageconverterandlombok.dto.NormalDto;
import com.practice.springmessageconverterandlombok.dto.OneDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.beans.Introspector;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void test_lombok_dto() throws Exception {
        LombokDto lombokDto = LombokDto.builder()
                .aaa("aaa").Bbb("Bbb").cCc("cCc").ddD("ddD")
                .EEe("EEe").FfF("FfF").gGG("gGG").HHH("HHH")
                .XXxX("XXxX").YYYy("YYYy").ZZzz("ZZzz")
                .aHello("aHello").HELLOworLD("HELLOworLD")
                .build();

        String content = objectMapper.writeValueAsString(lombokDto);

        this.mockMvc.perform(post("/lombok")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void test_normal_dto() throws Exception {
        NormalDto normalDto = NormalDto.builder()
                .aaa("aaa").Bbb("Bbb").cCc("cCc").ddD("ddD")
                .EEe("EEe").FfF("FfF").gGG("gGG").HHH("HHH")
                .XXxX("XXxX").YYYy("YYYy").ZZzz("ZZzz")
                .aHello("aHello").HELLOworLD("HELLOworLD")
                .build();

        String content = objectMapper.writeValueAsString(normalDto);

        this.mockMvc.perform(post("/normal")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}