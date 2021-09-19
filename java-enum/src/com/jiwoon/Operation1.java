package com.jiwoon;

/*
 * Bad Case
 *
 * public enum Operation {
 *     PLUS, MINUS, TIMES, DIVIDE;
 *
 *     // 상수가 뜻하는 연산을 수행한다.
 *     public double apply(double x, double y) {
 *         switch (this) {
 *             case PLUS: return x + y;
 *             case MINUS: return x - y;
 *             case TIMES: return x * y;
 *             case DIVIDE: return x / y;
 *         }
 *         throw new AssertionError("알 수 없는 연산: " + this);
 *     }
 * }
 */

/** 상수별 메소드 구현 (Constant-specific Method Implementation) */
public enum Operation1 {
    PLUS   { public double apply(double x, double y) { return x + y; }},
    MINUS  { public double apply(double x, double y) { return x - y; }},
    TIMES  { public double apply(double x, double y) { return x * y; }},
    DIVIDE { public double apply(double x, double y) { return x / y; }};

    public abstract double apply(double x, double y);
}