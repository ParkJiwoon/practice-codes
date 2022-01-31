package strategy._03_template_callback.code;

import org.springframework.util.StopWatch;

public class TimeLogTemplate {

    public void execute(Callback callback) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        // 비즈니스 로직 시작
        callback.call();
        // 비즈니스 로직 종료

        stopWatch.stop();
        System.out.println("실행 시간 = " + stopWatch.getTotalTimeMillis());
    }
}
