package template_method._02_after.code;

import org.springframework.util.StopWatch;

public abstract class AbstractTemplate {

    public void execute() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        // 비즈니스 로직 시작
        call();
        // 비즈니스 로직 종료

        stopWatch.stop();
        System.out.println("실행 시간 = " + stopWatch.getTotalTimeMillis());
    }

    protected abstract void call();
}
