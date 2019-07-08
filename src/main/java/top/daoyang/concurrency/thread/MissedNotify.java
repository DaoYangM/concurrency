package top.daoyang.concurrency.thread;

import netscape.security.UserTarget;

public class MissedNotify {

    private final static Object LOCK = new Object();

    private void await()  {
        System.out.println("wait start");
        synchronized (LOCK) {
            try {
                LOCK.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("wait interrupt");
            }
        }
        System.out.println("wait end");
    }

    private void notifyAwait() throws InterruptedException {
        System.out.println("notify start");
        synchronized (LOCK) {
            LOCK.notifyAll();
            Thread.sleep(100000);
        }
        System.out.println("notify end");
    }

    public static void main(String[] args) throws InterruptedException {
        MissedNotify missedNotify = new MissedNotify();

        Thread waitThread = new Thread(() -> {
            try {
                Thread.sleep(500);
                missedNotify.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        waitThread.start();

        new Thread(() -> {
            try {
                Thread.sleep(100);
                missedNotify.notifyAwait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        Thread.sleep(5000);
        waitThread.interrupt();
        System.out.println("after main interrupt");
    }
}
