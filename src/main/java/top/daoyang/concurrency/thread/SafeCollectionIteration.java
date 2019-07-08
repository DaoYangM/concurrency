package top.daoyang.concurrency.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SafeCollectionIteration {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        list = Collections.synchronizedList(list);

        synchronized (list) {

            for (Integer integer : list) {
                System.out.println(integer);
            }
        }
    }
}
