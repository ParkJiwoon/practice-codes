package com.jiwoon;

import java.util.EnumSet;
import java.util.Set;

/**
 * 비트 필드 사용
 *
 * public class Text {
 *     public static final int STYLE_BOLD          = 1 << 0;   // 1
 *     public static final int STYLE_ITALIC        = 1 << 1;   // 2
 *     public static final int STYLE_UNDERLINE     = 1 << 2;   // 4
 *     public static final int STYLE_STRIKETHROUGH = 1 << 3;   // 8
 *
 *     // 매개변수 styles 는 0 개 이상의 STYLE_ 상수를 비트별 OR 한 값
 *     public void applyStyles(int styles) {
 *         // ...
 *     }
 * }
 *
 *
 * text.applyStyles(STYLE_BOLD | STYLE_ITALIC);
 */


public class Text {
    public enum Style { BOLD, ITALIC, UNDERLINE, STRIKETHROUGH }

    public void applyStyles(Set<Style> styles) {
        // ...
    }
}

// usage
// text.applyStyles(EnumSet.of(Text.Style.BOLD, Text.Style.ITALIC));
