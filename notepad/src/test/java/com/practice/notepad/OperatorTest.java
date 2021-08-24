package com.practice.notepad;

import com.practice.notepad.enums.Operator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class OperatorTest {

    @Test
    void testTranslateEnToKr1() {
        assertThat(Operator.translateEnToKr1("PLUS")).isEqualTo("더하기");
        assertThat(Operator.translateEnToKr1("MINUS")).isEqualTo("빼기");
        assertThat(Operator.translateEnToKr1("DIVIDE")).isNull();

        assertThat(Operator.translateEnToKr2("PLUS")).isEqualTo("더하기");
        assertThat(Operator.translateEnToKr2("MINUS")).isEqualTo("빼기");
        assertThat(Operator.translateEnToKr2("DIVIDE")).isNull();
    }

    @Test
    void testConcat() {
        assertThat(Operator.PLUS.concat(" ONE")).isEqualTo("PLUS ONE");
        assertThat(Operator.MINUS.concat(" ONE")).isEqualTo("MINUS ONE");
    }
}
