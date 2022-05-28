package _01_factory_method.factory_method.model;

public class NaverDeveloper implements Developer {
    @Override
    public void registerEmail() {
        System.out.println("네이버 이메일 등록");
    }

    @Override
    public void allowAccess() {
        System.out.println("네이버 회사 권한 허용");
    }
}
