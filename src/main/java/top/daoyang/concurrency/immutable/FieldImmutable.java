package top.daoyang.concurrency.immutable;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import java.util.Map;

@Slf4j
public class FieldImmutable {

    private final static Map<Integer, Integer> map  = Maps.newHashMap();

    static {
        map.put(1, 1);
    }

    public static void main(String[] args) {
        log.info("Map 1: {}", map.get(1));
    }
}
