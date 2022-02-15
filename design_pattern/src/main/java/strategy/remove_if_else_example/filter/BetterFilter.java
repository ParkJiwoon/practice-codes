package strategy.remove_if_else_example.filter;

import strategy.remove_if_else_example.specification.Spec;

import java.util.ArrayList;
import java.util.List;

public class BetterFilter<T> implements Filter<T> {

    @Override
    public List<T> filter(List<T> items, Spec<T> spec) {
        List<T> result = new ArrayList<>();

        for (T item : items) {
            if (spec.isSatisfied(item)) {
                result.add(item);
            }
        }

        return result;
    }
}
