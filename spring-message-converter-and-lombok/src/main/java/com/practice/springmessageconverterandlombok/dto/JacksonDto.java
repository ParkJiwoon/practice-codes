package com.practice.springmessageconverterandlombok.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
public class JacksonDto {
    private String name;

    public String getNameChange() {
        return name;
    }
}
