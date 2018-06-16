package top.daoyang.concurrency.test.commonUnsafe.date;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import top.daoyang.concurrency.annotation.ThreadSafe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@ThreadSafe
public class JodaTimeTest {

    private static int clientTotal = 5000;

    private static int threadTotal = 50;

    private static DateTimeFormatter dateTimeFormat = DateTimeFormat.forPattern("yyyyMMdd");

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
    }

    public static void add() {
        log.info("format time: {}", dateTimeFormat.parseDateTime("20180615"));
    }
}
