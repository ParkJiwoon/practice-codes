package com.jiwoon;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Operation2 {
    PLUS("+"),
    MINUS("-"),
    TIMES("*"),
    DIVIDE("/")
    ;

    private final String symbol;

    Operation2(String symbol) {
        this.symbol = symbol;
    }

    public String symbol() {
        return symbol;
    }

    // 생성과 동시에 캐싱
    private static final Map<String, Operation2> BY_SYMBOL =
            Stream.of(values()).collect(Collectors.toMap(Operation2::symbol, e -> e));

    // 캐싱된 값으로 한번에 찾을 수 있음
    // Optional 로 감싸서 클라이언트에게 Nullable 한 데이터임을 알려줌
    public static Optional<Operation2> valueOfSymbol(String symbol) {
        return Optional.ofNullable(BY_SYMBOL.get(symbol));
    }

    // Cache 적용 안한 케이스
//    public static Optional<Operation2> valueOfSymbol(String symbol) {
//        return Stream.of(values())
//                .filter(v -> v.symbol.equals(symbol))
//                .findAny();
//    }
}
