package top.daoyang.concurrency.test.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class SynchronizedStatic {
    public static synchronized void test() {
        for (int i = 0; i < 10; i++) {
            log.info("static test: {}", i);
        }
    }

    public static void main(String[] args) {

        SynchronizedNormal synchronizedNormal1 = new SynchronizedNormal();
        SynchronizedNormal synchronizedNormal2 = new SynchronizedNormal();

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            synchronizedNormal1.test();
        });

        executorService.execute(() -> {
            synchronizedNormal2.test();
        });
    }
}
