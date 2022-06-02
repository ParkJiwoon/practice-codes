package _04_strategy.remove_if_else_example.specification;

public class AndSpec<T> implements Spec<T> {

    private final Spec<T> first;
    private final Spec<T> second;

    public AndSpec(Spec<T> first, Spec<T> second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean isSatisfied(T item) {
        return first.isSatisfied(item) && second.isSatisfied(item);
    }
}
