package _01_factory_method.factory_method.model;

public class KakaoUser implements User {
    @Override
    public void signup() {
        System.out.println("카카오 아이디로 가입");
    }
}
