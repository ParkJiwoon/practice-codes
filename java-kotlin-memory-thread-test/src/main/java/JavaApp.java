import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

public class JavaApp {
    public static void main(String[] args) {
        runExecutor();
    }

    private static void runAsync() {
        System.out.println("runAsync 처음 쓰레드 갯수 : " + Thread.activeCount());

        IntStream.range(1, 100).parallel().peek(i -> {
            System.out.println("name : " + Thread.currentThread().getName() + ", count: " + Thread.activeCount());
        }).sum();

        sleep(1000L);
        System.out.println("runAsync 마지막 쓰레드 갯수 : " + Thread.activeCount());
    }

    private static void runCompletableFuture() {
        System.out.println("runCompletableFuture 처음 쓰레드 갯수 : " + Thread.activeCount());

        IntStream.range(1, 100).forEach(i -> {
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                System.out.println("name : " + Thread.currentThread().getName() + ", count: " + Thread.activeCount());
            });
        });
        sleep(1000L);

        System.out.println("runCompletableFuture 마지막 쓰레드 갯수 : " + Thread.activeCount());
    }

    private static void runForkJoinPool() {
        System.out.println("runForkJoinPool 처음 쓰레드 갯수 : " + Thread.activeCount());

        ForkJoinPool forkJoinPool = new ForkJoinPool();

        IntStream.range(1, 100).forEach(i -> {
            forkJoinPool.submit(() -> {
                System.out.println("name : " + Thread.currentThread().getName() + ", count: " + Thread.activeCount());
            });
        });

        sleep(1000L);

        System.out.println("runForkJoinPool 마지막 쓰레드 갯수 : " + Thread.activeCount());
    }

    private static void runExecutor() {
        System.out.println("runExecutor 처음 쓰레드 갯수 : " + Thread.activeCount());

        ExecutorService executor = Executors.newFixedThreadPool(100);

        IntStream.range(1, 100).forEach(i -> {
            CompletableFuture.runAsync(() -> {
                System.out.println("name : " + Thread.currentThread().getName() + ", count: " + Thread.activeCount());
            }, executor);
        });

        executor.shutdown();

        sleep(1000L);

        System.out.println("runExecutor 마지막 쓰레드 갯수 : " + Thread.activeCount());
    }

    private static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
