package strategy._02_after.code;

public class StrategyLogic1 implements Strategy {

    @Override
    public void call() {
        System.out.println("비즈니스 로직 1 실행");
    }
}
