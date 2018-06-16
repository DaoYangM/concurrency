package top.daoyang.concurrency.test.commonUnsafe.string;

import lombok.extern.slf4j.Slf4j;
import top.daoyang.concurrency.annotation.ThreadSafe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@ThreadSafe
public class StringBufferTest {

    private static int clientTotal = 5000;

    private static int threadTotal = 50;

    private static StringBuffer StringBuffer = new StringBuffer();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                add();
                countDownLatch.countDown();
                semaphore.release();
            });
        }

        countDownLatch.await();
        executorService.shutdown();

        log.info("count: {}", StringBuffer.length());
    }

    public static void add() {
        StringBuffer.append("1");
    }
}

