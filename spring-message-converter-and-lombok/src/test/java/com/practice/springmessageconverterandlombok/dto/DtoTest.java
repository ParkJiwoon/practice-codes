package com.practice.springmessageconverterandlombok.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

public class DtoTest {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void test_lombok_dto_to_json_string() throws JsonProcessingException {
        LombokDto lombokDto = LombokDto.builder()
                .aaa("aaa").Bbb("Bbb").cCc("cCc").ddD("ddD")
                .EEe("EEe").FfF("FfF").gGG("gGG").HHH("HHH")
                .XXxX("XXxX").YYYy("YYYy").ZZzz("ZZzz")
                .aHello("aHello").HELLOworLD("HELLOworLD")
                .build();

        String content = objectMapper.writeValueAsString(lombokDto);
        System.out.println("LombokDto to Json");
        System.out.println(content);
    }

    @Test
    void test_normal_dto_to_json_string() throws JsonProcessingException {
        NormalDto normalDto = NormalDto.builder()
                .aaa("aaa").Bbb("Bbb").cCc("cCc").ddD("ddD")
                .EEe("EEe").FfF("FfF").gGG("gGG").HHH("HHH")
                .XXxX("XXxX").YYYy("YYYy").ZZzz("ZZzz")
                .aHello("aHello").HELLOworLD("HELLOworLD")
                .build();

        String content = objectMapper.writeValueAsString(normalDto);
        System.out.println("NormalDto to Json");
        System.out.println(content);
    }

    @Test
    void test_jackson_dto() throws Exception {
        JacksonDto jacksonDto = new JacksonDto("my name");
        String content = objectMapper.writeValueAsString(jacksonDto);

        // Jackson : {"nameChange":"my name"}
        System.out.println("Jackson : " + content);
    }

    @Test
    void test_one_dto() throws JsonProcessingException {
        OneDto oneDto = new OneDto();
        String content = objectMapper.writeValueAsString(oneDto);

        System.out.println("Jackson : " + content);
    }

    @Test
    void test_two_dto() throws JsonProcessingException {
        TwoDto twoDto = new TwoDto();
        String content = objectMapper.writeValueAsString(twoDto);

        System.out.println("Jackson : " + content);
    }
}
