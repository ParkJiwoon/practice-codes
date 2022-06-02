package _04_strategy._02_after;

import _04_strategy._02_after.code.Context;
import _04_strategy._02_after.code.Strategy;
import _04_strategy._02_after.code.StrategyLogic1;
import _04_strategy._02_after.code.StrategyLogic2;
import strategy._02_after.code.*;

public class AfterStrategyApp {

    public static void main(String[] args) {
        Strategy strategy1 = new StrategyLogic1();
        Context context1 = new Context(strategy1);
        context1.execute();

        Strategy strategy2 = new StrategyLogic2();
        Context context2 = new Context(strategy2);
        context2.execute();
    }
}
