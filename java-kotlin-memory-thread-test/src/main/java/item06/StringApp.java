package item06;

import java.util.regex.Pattern;

public class StringApp {
    public static void main(String[] args) {
        String a = "example";
        String b = "example";
        String c = a;
        String d = new String("example");
        String e = new String(a);

        System.out.println(a == b);         // true
        System.out.println(a.equals(b));    // true

        System.out.println(b == c);         // true : a, b, c 는 같은 객체를 바라보고 있음
        System.out.println(b.equals(c));    // true

        System.out.println(d == a);         // false : new 로 생성해서 서로 다른 객체
        System.out.println(d.equals(a));    // true

        System.out.println(e == a);         // false : new 로 생성해서 서로 다른 객체
        System.out.println(e.equals(a));    // true
    }

    // bad case: 내부적으로 매번 Pattern 인스턴스를 생성해서 비효율적
    boolean isRomanNumeral1(String s) {
        return s.matches("^(?=.)M*");
    }

    // good case: Pattern 인스턴스를 미리 생성해서 캐싱해두자
    private static final Pattern ROMAN = Pattern.compile("^(?=.)M*");

    boolean isRomanNumeral2(String s) {
        return ROMAN.matcher(s).matches();
    }

    private static long sum() {
        // bad case: 참조 타입을 사용해서 값을 더할때마다 오토 박싱이 발생함
        Long sum1 = 0L;

        // good case
        long sum2 = 0L;

        for (long i = 0; i < 10; i++) {
            sum2 += i;
        }

        return sum2;
    }
}
