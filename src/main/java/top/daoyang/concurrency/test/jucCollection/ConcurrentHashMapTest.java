package top.daoyang.concurrency.test.jucCollection;

import lombok.extern.slf4j.Slf4j;
import top.daoyang.concurrency.annotation.ThreadSafe;

import java.util.Map;
import java.util.concurrent.*;

@Slf4j
@ThreadSafe
public class ConcurrentHashMapTest {

    private static int clientTotal = 5000;

    private static int threadTotal = 50;

    private static Map<Integer, Integer> map = new ConcurrentHashMap<>();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        for (int i = 0; i < clientTotal; i++) {
            final int a = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                add(a);
                countDownLatch.countDown();
                semaphore.release();
            });
        }

        countDownLatch.await();
        executorService.shutdown();

        log.info("count: {}", map.size());
    }

    public static void add(int i) {
        map.put(i, 1);
    }

}
