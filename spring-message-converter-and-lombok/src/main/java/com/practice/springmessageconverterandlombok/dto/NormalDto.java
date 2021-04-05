package com.practice.springmessageconverterandlombok.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NormalDto {
    private String aaa;

    private String Bbb;
    private String cCc;
    private String ddD;

    private String EEe;
    private String FfF;
    private String gGG;

    private String HHH;

    private String ZZzz;
    private String XXxX;
    private String YYYy;

    private String aHello;     // 가설 2
    private String HELLOworLD; // 가설 4

    /*
     *  결론적으로 jackson 의 규칙은 다음과 같다.
     *  1. `get` 이후의 두 글자를 확인한다.
     *  2. getAaaa -> aaaa : 첫 문자만 대문자인 경우 맨 앞 글자만 소문자로 치환한다.
     *  3. getaaaa -> aaaa : 맨 앞이 소문자인 경우 그대로 사용한다.
     *     getaAAa -> aAAa : 뒤에 대문자가 나와도 상관없음
     *  4. getAAAa -> aaaa : 둘다 대문자라면 이어진 대문자 모두를 소문자로 치환한다. 그 뒤에 소문자 이후 다시 대문자가 나오는건 대문자로 치환
     */

    public String getAaa() {    // aaa (expect) o aaa (actual) (가설1 일치)
        return aaa;
    }

    public String getBbb() {    // bbb (expect) o bbb (actual) (가설1 일치)
        return Bbb; // null
    }

    public String getcCc() {    // cCc (expect) o cCc (actual) (가설2 일치)
        return cCc;
    }

    public String getDdD() {    // ddD (expect) o ddD (actual) (가설1 일치)
        return ddD;
    }

    public String getEEe() {    // eEe (expect) x eee (actual)
        return EEe; // null
    }

    public String getFfF() {    // ffF (expect) o ffF (actual) (가설1 일치)
        return FfF; // null
    }

    public String getgGG() {    // gGG (expect) o gGG (actual) (가설2 일치)
        return gGG;
    }

    public String getHHH() {    // hHH (expect) x hhh (actual)
        return HHH; // null
    }

    public String getZZzz() {   // zzzz (expect) o zzzz (actual) (가설3 일치)
        return ZZzz;
    }

    public String getXXxX() {   // xxxx (expect) x xxxX (actual)
        return XXxX;
    }

    public String getYYYy() {   // yyyy (expect) o yyyy (actual) (가설 3 일치)
        return YYYy;
    }

    public String getaHello() { // aHello (expect) o aHello (actual) (가설 2 일치)
        return aHello;
    }

    public String getHELLOworLD() { // helloworLD (expect) o helloworLD (actual) (가설 4 일치)
        return HELLOworLD;
    }
}
