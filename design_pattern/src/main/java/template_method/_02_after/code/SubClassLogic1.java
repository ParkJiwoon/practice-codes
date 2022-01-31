package template_method._02_after.code;

public class SubClassLogic1 extends AbstractTemplate {

    @Override
    protected void call() {
        System.out.println("비즈니스 로직 1 실행");
    }
}
