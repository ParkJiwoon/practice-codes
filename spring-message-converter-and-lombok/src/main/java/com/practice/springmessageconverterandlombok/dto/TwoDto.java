package com.practice.springmessageconverterandlombok.dto;

import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
public class TwoDto {
    private String aaaa;
    private String bbbB;

    private String Cccc;
    private String DddD;

    private String eEee;
    private String fFfF;

    public String getAaaa() {
        return aaaa;
    }

    public String getBbbB() {
        return bbbB;
    }

    public String getCccc() {
        return Cccc;
    }

    public String getDddD() {
        return DddD;
    }

    public String geteEee() {
        return eEee;
    }

    public String getfFfF() {
        return fFfF;
    }
}
