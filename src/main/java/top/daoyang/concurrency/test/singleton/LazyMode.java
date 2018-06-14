package top.daoyang.concurrency.test.singleton;

import lombok.extern.slf4j.Slf4j;
import top.daoyang.concurrency.annotation.NotThreadSafe;

@Slf4j
@NotThreadSafe
public class LazyMode {

    private LazyMode() {}

    private volatile static LazyMode lazyMode = null;

    private static LazyMode getLazyMode() {

        if (lazyMode == null)
            synchronized(LazyMode.class) {
                if (lazyMode == null)
                    lazyMode = new LazyMode();
            }

        return lazyMode;
    }
}
