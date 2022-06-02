package _04_strategy._02_after.code;

public class StrategyLogic2 implements Strategy {

    @Override
    public void call() {
        System.out.println("비즈니스 로직 2 실행");
    }
}
