package _04_strategy.remove_if_else_example.filter;

import _04_strategy.remove_if_else_example.specification.Spec;

import java.util.List;

public interface Filter<T> {
    List<T> filter(List<T> items, Spec<T> spec);
}
