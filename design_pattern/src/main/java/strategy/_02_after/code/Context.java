package strategy._02_after.code;

import org.springframework.util.StopWatch;

/**
 * 필드에 전략을 보관하는 방식
 */
public class Context {

    private final Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public void execute() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        // 비즈니스 로직 시작
        strategy.call();
        // 비즈니스 로직 종료

        stopWatch.stop();
        System.out.println("실행 시간 = " + stopWatch.getTotalTimeMillis());
    }
}
