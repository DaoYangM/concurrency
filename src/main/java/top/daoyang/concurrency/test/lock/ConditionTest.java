package top.daoyang.concurrency.test.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class ConditionTest {

    private final static ReentrantLock lock = new ReentrantLock();

    private final static Condition condition = lock.newCondition();

    public static void main(String[] args) {

        new Thread(() -> {
            lock.lock();
            log.info("Thread1: {} get lock", Thread.currentThread().getName());
            try {
                log.info("Thread1: {} condition await", Thread.currentThread().getName());
                condition.await(); // release lock;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("Thread1: {} unlock", Thread.currentThread().getName());
            lock.unlock();
        }).start();

        new Thread(() -> {
            lock.lock();
            log.info("Thread2: {} get lock", Thread.currentThread().getName());

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            condition.signalAll();
            log.info("Thread2: {} signalAll", Thread.currentThread().getName());
            lock.unlock();
        }).start();
    }
}
