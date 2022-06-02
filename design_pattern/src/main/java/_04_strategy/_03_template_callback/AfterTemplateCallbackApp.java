package _04_strategy._03_template_callback;

import _04_strategy._03_template_callback.code.TimeLogTemplate;

public class AfterTemplateCallbackApp {

    public static void main(String[] args) {
        TimeLogTemplate timeLogTemplate = new TimeLogTemplate();
        timeLogTemplate.execute(() -> System.out.println("비즈니스 로직 1 실행"));
        timeLogTemplate.execute(() -> System.out.println("비즈니스 로직 2 실행"));
    }
}
