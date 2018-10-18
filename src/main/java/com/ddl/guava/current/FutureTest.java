package com.ddl.guava.current;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author dongdongliu
 * @version 1.0
 */
public class FutureTest {
    /**
     * 多个任务异步执行，使用线程池，并行执行
     * 这里任务抽象成一组数字，线程内睡相应数字的秒数，然后利用future返回结果
     * 任务在for循环添加进service里，submit后就开始执行，随后get时会阻塞的拿结果，如果结果还没返回（还在睡中）就一直阻塞
     * 当任务顺序为1，2，3。。。。时，在1s后会返回1，以此类推，在1s取得第一个结果时，其他任务也执行了1s，所以再过1s，第二个任务就会返回，共执行2s。以此类推。
     * 这样会有一个问题，就是当任务变成6，5，4.。。时，第一个任务的获取时间会达到6s，由于future的get方法是阻塞的，所以别的任务在6s时已经执行完了，但是由于
     * 第一个get方法还在阻塞，所以未能执行到get方法，不会返回结果，当第一个很慢的任务返回后，低于它处理时间的其他任务结果get方法会瞬间返回。
     * 这样处理的总时间是不变的，但是由于第一个任务的很慢，导致已经有了结果的任务拿不到结果，所以要用guava的listenableFuture。
     */
    public static void main(String[] args) throws Exception {
        long t1 = System.currentTimeMillis();
        ExecutorService service = Executors.newFixedThreadPool(12);
        List<Integer> list = Lists.newArrayList(6, 5, 4, 3, 2, 1);
        List<Future> futureList = Lists.newArrayListWithExpectedSize(6);
        for (Integer item : list) {
            Future future = service.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    TimeUnit.SECONDS.sleep(item);
                    return item;
                }
            });
            futureList.add(future);
        }

        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);

        try {

            for (Future future : futureList) {
                System.out.println("future:" + future.get());
            }
            long t3 = System.currentTimeMillis();
            System.out.println(t3 - t2);
            System.out.println(t3 - t1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            service.shutdown();
        }
    }
}
