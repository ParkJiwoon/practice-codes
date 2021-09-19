package com.jiwoon;

/*
 * Bad Case
 *
 * public enum Fruit {
 *     APPLE, ORANGE, BANANA, STRAWBERRY;
 *
 *     public void printColor() {
 *         switch (this) {
 *             case APPLE:
 *             case STRAWBERRY:
 *                 System.out.println("This is Red");
 *                 break;
 *             default:
 *                 System.out.println("This is Not Red");
 *         }
 *     }
 * }
 */

/** 전략 열거 타입 */
public enum Fruit {
    APPLE(ColorType.RED),
    ORANGE(ColorType.OTHER),
    BANANA(ColorType.OTHER),
    STRAWBERRY(ColorType.RED);

    private final ColorType colorType;

    Fruit(ColorType colorType) {
        this.colorType = colorType;
    }

    public void printColor() {
        colorType.printColor();
    }

    enum ColorType {
        RED {
            void printColor() {
                System.out.println("This is Red");
            }
        },
        OTHER {
            void printColor() {
                System.out.println("This is Not Red");
            }
        };

        abstract void printColor();
    }
}