package top.daoyang.concurrency.test.Atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

@Slf4j
public class AtomicIntegerFieldUpdaterReferenceTest {

    private volatile int count = 0;

    private static AtomicIntegerFieldUpdater atomicIntegerFieldUpdater =
            AtomicIntegerFieldUpdater.newUpdater(AtomicIntegerFieldUpdaterReferenceTest.class, "count");

    public static void main(String[] args) {

        AtomicIntegerFieldUpdaterReferenceTest test = new AtomicIntegerFieldUpdaterReferenceTest();
        atomicIntegerFieldUpdater.compareAndSet(test, 0, 100);

        log.info("result: {}", atomicIntegerFieldUpdater.get(test));
    }
}
