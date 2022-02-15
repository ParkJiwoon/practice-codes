package strategy.remove_if_else_example.specification;

public interface Spec<T> {
    boolean isSatisfied(T item);
}
