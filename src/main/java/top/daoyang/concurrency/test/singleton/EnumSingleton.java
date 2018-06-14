package top.daoyang.concurrency.test.singleton;

import lombok.extern.slf4j.Slf4j;
import top.daoyang.concurrency.annotation.Recommend;
import top.daoyang.concurrency.annotation.ThreadSafe;

@Slf4j
@ThreadSafe
@Recommend
public class EnumSingleton {

    private EnumSingleton() {}

    public static EnumSingleton getEnumSingleton() {
        return Sub.INSTANCE.priEnumSingleton();
    }

    private enum Sub {
        INSTANCE;

        private EnumSingleton enumSingleton = null;

        Sub() {
            enumSingleton = new EnumSingleton();
        }

        public EnumSingleton priEnumSingleton() {
            return enumSingleton;
        }
    }

    public static void main(String[] args) {
        log.info("EnumSingleton: {}", getEnumSingleton());
    }
}
