package template_method._01_before;

import org.springframework.util.StopWatch;

public class BeforeTemplateMethodApp {

    public static void main(String[] args) {
        logic1();
        logic2();
    }

    private static void logic1() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        // 비즈니스 로직 시작
        System.out.println("비즈니스 로직 1 실행");
        // 비즈니스 로직 종료

        stopWatch.stop();
        System.out.println("실행 시간 = " + stopWatch.getTotalTimeMillis());
    }

    private static void logic2() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        // 비즈니스 로직 시작
        System.out.println("비즈니스 로직 2 실행");
        // 비즈니스 로직 종료

        stopWatch.stop();
        System.out.println("실행 시간 = " + stopWatch.getTotalTimeMillis());
    }
}
