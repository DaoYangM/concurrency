package top.daoyang.concurrency.test.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class SynchronizedNormal {

    public void test() {
        synchronized(this) {
            for (int i = 0; i < 10; i++) {
                log.info("test 1: {}", i);
            }
        }
    }

    public synchronized void test2() {}

    public static void main(String[] args) {
        SynchronizedNormal normal = new SynchronizedNormal();

        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.execute(() -> {
            normal.test();
        });

        executorService.execute(() -> {
            normal.test();
        });
    }
}
