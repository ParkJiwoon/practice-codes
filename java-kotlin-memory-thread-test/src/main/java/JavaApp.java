public class JavaApp {
    public static void main(String[] args) {
        JavaUser user = new JavaUser("woody", 30);

        System.out.println(Thread.activeCount());
        System.out.println(user);
    }
}
