package top.daoyang.concurrency.test.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class CyclicBarrierTest {

    private final static CyclicBarrier cyclicBarrier = new CyclicBarrier(5);

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            executorService.execute(() -> {
                try {
                    test();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        executorService.shutdown();
    }

    public static void test() throws BrokenBarrierException, InterruptedException {
        log.info("Thread: {} await", Thread.currentThread().getName());
        cyclicBarrier.await();

        log.info("Thread: {} continue", Thread.currentThread().getName());
    }
}
