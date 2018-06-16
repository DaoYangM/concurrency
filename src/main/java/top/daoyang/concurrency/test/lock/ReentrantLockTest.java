package top.daoyang.concurrency.test.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class ReentrantLockTest {

    private static int clientTotal = 5000;

    private static int threadTotal = 50;

    private static int count = 0;

    private final static ReentrantLock lock = new ReentrantLock();

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
                lock.unlock();
            });
        }

        countDownLatch.await();
        executorService.shutdown();
        log.info("count: {}",  count);
    }

    public static void add() {
        lock.lock();
        count++;

    }
}
