package _01_factory_method.factory_method.model;

public class KakaoDeveloper implements Developer {
    @Override
    public void registerEmail() {
        System.out.println("카카오 이메일 등록");
    }

    @Override
    public void allowAccess() {
        System.out.println("카카오 회사 권한 허용");
    }
}
