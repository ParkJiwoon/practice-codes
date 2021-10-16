package com.example.springwebfluxmongodb.config;

import com.example.springwebfluxmongodb.config.httptrace.HttpTraceWrapper;
import com.example.springwebfluxmongodb.config.httptrace.HttpTraceWrapperRepository;
import com.example.springwebfluxmongodb.config.httptrace.SpringDataHttpTraceRepository;
import org.bson.Document;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.convert.NoOpDbRefResolver;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import java.net.URI;
import java.util.Collections;
import java.util.Map;

@Configuration
public class ActuatorConfig {

    /**
     * In Memory 기반이기 때문에 트레이스 정보는 현재 인스턴스에만 존재
     * 인스턴스를 재시작하면 그동안의 트레이스 정보가 소멸됨
     * 만약 클라우드에서 여러 대의 노드에 수십만 사용자의 웹 요청을 추적하고 싶다면 HttpTraceRepository 구현체를 직접 만들어야 함
     */
    public HttpTraceRepository traceRepository() {
        return new InMemoryHttpTraceRepository();
    }

    @Bean
    public HttpTraceRepository springDataTraceRepository(HttpTraceWrapperRepository repository) {
        return new SpringDataHttpTraceRepository(repository);
    }

    public static Converter<Document, HttpTraceWrapper> CONVERTER =
            new Converter<Document, HttpTraceWrapper>() {
                @Override
                public HttpTraceWrapper convert(Document document) {
                    Document httpTrace = document.get("httpTrace", Document.class);
                    Document request = httpTrace.get("request", Document.class);
                    Document response = httpTrace.get("response", Document.class);

                    return new HttpTraceWrapper(new HttpTrace(
                            new HttpTrace.Request(
                                    request.getString("method"),
                                    URI.create(request.getString("uri")),
                                    request.get("headers", Map.class),
                                    null),
                            new HttpTrace.Response(
                                    response.getInteger("status"),
                                    response.get("headers", Map.class)),
                            httpTrace.getDate("timestamp").toInstant(),
                            null,
                            null,
                            httpTrace.getLong("timeTaken")));
                }
            };

    @Bean
    public MappingMongoConverter mappingMongoConverter(MongoMappingContext context) {

        MappingMongoConverter mappingConverter =
                new MappingMongoConverter(NoOpDbRefResolver.INSTANCE, context);

        mappingConverter.setCustomConversions(
                new MongoCustomConversions(Collections.singletonList(CONVERTER)));

        return mappingConverter;
    }
}
