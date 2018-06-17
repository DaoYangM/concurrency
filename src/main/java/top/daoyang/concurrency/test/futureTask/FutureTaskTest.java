package top.daoyang.concurrency.test.futureTask;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@Slf4j
public class FutureTaskTest {

    private final static FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
        @Override
        public String call() throws Exception {
            log.info("FutureTask in work");
            Thread.sleep(5000);
            log.info("FutureTask end");

            return "DONE";
        }
    });

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        new Thread(futureTask).start();

        log.info("Main start");
        Thread.sleep(2000);
        log.info("Main end");

        log.info("FutureTask result: {}", futureTask.get());
    }
}
