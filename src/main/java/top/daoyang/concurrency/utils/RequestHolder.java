package top.daoyang.concurrency.utils;

public class RequestHolder {

    private static ThreadLocal<Long> threadLocal = new ThreadLocal();

    public static void add(long i) {
        threadLocal.set(i);
    }

    public static Long get() {
        return threadLocal.get();
    }

    public static void remove() {
        threadLocal.remove();
    }
}
