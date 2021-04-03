package com.jiwoon.practicewebmvc.scope;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class ProtoTypeTest {

    @Test
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        // getBean 을 호출하는 시점에 생성해서 반환
        System.out.println("find prototypeBean1");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);

        // getBean 을 호출하는 시점에 생성해서 반환
        System.out.println("find prototypeBean2");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);

        // Prototype Scope 라서 둘은 다름
        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);
        assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

        // 프로토타입 스코프의 관리 책임은 이제 클라이언트에 있어서 @Destroy 호출되지 않음
        // 필요하다면 prototypeBean1.destroy(); 처럼 클라에서 직접 호출
        ac.close();
    }

    @Scope("prototype")
    static class PrototypeBean {
        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }

    @Scope(value = "singleton")
    static class SingletonBean {

        // 프로토타입 빈을 직접 받는게 아니라 호출 시점에 컨테이너 빈을 요청하는 Provider
        @Autowired
        private ObjectProvider<PrototypeBean> prototypeBeansProvider;

        public void logic() {
            PrototypeBean prototypeBean = prototypeBeansProvider.getObject();
            // ... logic
        }
    }

    @Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
    static class WebScopeBean {
        // ...
    }
}