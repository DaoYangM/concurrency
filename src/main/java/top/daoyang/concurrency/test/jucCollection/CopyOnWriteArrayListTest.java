package top.daoyang.concurrency.test.jucCollection;

import lombok.extern.slf4j.Slf4j;
import top.daoyang.concurrency.annotation.ThreadSafe;

import java.util.List;
import java.util.concurrent.*;

@Slf4j
@ThreadSafe
public class CopyOnWriteArrayListTest {

    private static int clientTotal = 5000;

    private static int threadTotal = 50;

    private static List<Integer> list = new CopyOnWriteArrayList<>();

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

        log.info("count: {}", list.size());
    }

    public static void add() {
        list.add(1);
    }
}
