import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class BigDecimalTest {

    @Test
    void test() {

        BigDecimal zero1 = new BigDecimal(0);
        BigDecimal zero2 = new BigDecimal(0.0);


        System.out.println(zero1);
        System.out.println(zero2);

        System.out.println(zero1.equals(zero2));
    }
}
