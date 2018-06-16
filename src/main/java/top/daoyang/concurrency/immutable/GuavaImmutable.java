package top.daoyang.concurrency.immutable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GuavaImmutable {

    private final static ImmutableList<Integer> list = ImmutableList.of(1, 2, 3);

    private final static ImmutableMap<Integer, String> map = ImmutableMap.<Integer, String>builder()
            .put(1, "1").build();

    public static void main(String[] args) {

    }
}
