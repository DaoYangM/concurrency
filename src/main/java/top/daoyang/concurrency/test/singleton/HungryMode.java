package top.daoyang.concurrency.test.singleton;

import lombok.extern.slf4j.Slf4j;
import top.daoyang.concurrency.annotation.ThreadSafe;

@Slf4j
@ThreadSafe
public class HungryMode {

    private HungryMode() {}

    private static HungryMode hungryMode = null;

    static {
        hungryMode = new HungryMode();
    }

    public static HungryMode getHungryMode() {
        return hungryMode;
    }

    public static void main(String[] args) {
        log.info("Hungry instance; {}", hungryMode);
    }
}
