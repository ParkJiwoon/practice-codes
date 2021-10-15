package com.example.springwebfluxmongodb.config.httptrace;

import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.data.annotation.Id;

/**
 * HttpTrace 인스턴스를 저장해야 하는데 HttpTrace 클래스 내부에는 Key 로 사용할 속성이 없음
 * 게다가 HttpTrace 는 final 로 선언되어 있어 상속도 불가능
 * HttpTrace 정보를 Repository 에 저장하기 위한 래퍼 클래스 필요
 */
public class HttpTraceWrapper {

    @Id
    private String id;

    private HttpTrace httpTrace;

    public HttpTraceWrapper(HttpTrace httpTrace) {
        this.httpTrace = httpTrace;
    }

    public HttpTrace getHttpTrace() {
        return httpTrace;
    }
}
