package _01_factory_method.factory_method.model;

public class NaverUser implements User {
    @Override
    public void signup() {
        System.out.println("네이버 아이디로 가입");
    }

    @Override
    public void login() {
        System.out.println("네이버 아이디로 로그인");
    }
}
