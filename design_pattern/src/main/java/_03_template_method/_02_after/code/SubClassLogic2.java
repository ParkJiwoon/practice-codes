package _03_template_method._02_after.code;

public class SubClassLogic2 extends AbstractTemplate {

    @Override
    protected void call() {
        System.out.println("비즈니스 로직 2 실행");
    }
}
