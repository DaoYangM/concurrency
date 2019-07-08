package top.daoyang.concurrency.thread;

public class DeadLock implements Runnable {

    private final Object L1;

    private final Object L2;

    private Long sleepTime;

    private DeadLock(Object l1, Object l2, Long sleepTime) {
        L1 = l1;
        L2 = l2;
        this.sleepTime = sleepTime;
    }

    @Override
    public void run() {
        synchronized (L1) {
            try {
                Thread.sleep(sleepTime);
                synchronized (L2) {
                    System.out.println(Thread.currentThread().getName() + ": " + "DONE");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Object L1 = new Object();
        Object L2 = new Object();

        new Thread(new DeadLock(L1, L2, 1000L)).start();
        new Thread(new DeadLock(L2, L1, 2000L)).start();
    }
}
