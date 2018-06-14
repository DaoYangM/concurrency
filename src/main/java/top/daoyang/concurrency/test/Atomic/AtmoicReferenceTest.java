package top.daoyang.concurrency.test.Atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;

@Slf4j
public class AtmoicReferenceTest {

    private static AtomicReference<Integer> atomicReference = new AtomicReference<>(0);
    public static void main(String[] args) {
        atomicReference.compareAndSet(0, 1);
        atomicReference.compareAndSet(1, 2);
        atomicReference.compareAndSet(0, 5);

        log.info("result: {}", atomicReference.get());
    }
}
