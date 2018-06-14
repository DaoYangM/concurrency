package top.daoyang.concurrency.test.Atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class AtomicBooleanTest {

    private static AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    public static void main(String[] args) {
        log.info("Main Thread: {} start", Thread.currentThread().getName());


        for (int i = 0; i < 1000; i++) {
            Thread thread1 = new Thread(new Test());

            atomicBoolean.set(true);
            atomicBoolean.compareAndSet(true, false);
            log.info("Thread: {} start", thread1.getName());
            thread1.start();
        }

        log.info("Test int: {}", Test.testInt);

        log.info("Test boolean: {}", atomicBoolean.get());

    }

    static class Test implements Runnable {

        private static AtomicInteger testInt = new AtomicInteger(0);

        @Override
        public void run() {
            add();
        }

        private void add() {
            testInt.incrementAndGet();
        }
    }
}
