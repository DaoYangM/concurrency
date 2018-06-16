package top.daoyang.concurrency.test.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CountDownLatchTest {

    private final static int count = 2000;

    private final static CountDownLatch countDownLatch = new CountDownLatch(count);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();

        for (int i = 0; i < count; i++) {
            final int a = i;
            service.execute(() -> {
                try {
                    test(a);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }

        countDownLatch.await(10, TimeUnit.MICROSECONDS);
        log.info("finished");
    }

    private static void test(int i) throws InterruptedException {
        Thread.sleep(1000);
        log.info("Test: {}", i);
    }
}
