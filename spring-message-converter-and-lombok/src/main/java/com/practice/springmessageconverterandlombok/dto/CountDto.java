package com.practice.springmessageconverterandlombok.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class CountDto {
    private int aCount;

    public int getaCount() {
        return aCount;
    }
}
