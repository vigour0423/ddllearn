package com.ddl.guava.current;

import com.google.common.collect.Lists;
import com.google.common.io.Resources;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author dongdongliu
 * @version 1.0
 */
public class ListenableFutureTest {
    /*
    创建线程池
    与java原始的future处理总时间是一样的，但是是非阻塞的，可以再第一个任务很慢的情况下，先返回后面不慢的任务的结果。
    */
    final static ListeningExecutorService service = MoreExecutors
            .listeningDecorator(newCachedThreadPool());

    /**
     * 调用newCachedThreadPool方法，
     * 可以创建一个缓冲型线程池，而在改方法中通过传参创建一个ThreadPoolExecutor。
     */
    public static ExecutorService newCachedThreadPool() {
        return new ThreadPoolExecutor(
                10,
                Runtime.getRuntime().availableProcessors() * 100,
                30L, TimeUnit.SECONDS,
                new SynchronousQueue<>(),
                new ThreadFactory() {
                    private final AtomicInteger threadNumber = new AtomicInteger(1);

                    @Override
                    public Thread newThread(Runnable r) {
                        return new Thread(r, "AsynsService-" + threadNumber.getAndIncrement());
                    }
                },
                new ThreadPoolExecutor.AbortPolicy()
        );
    }


    public static void main(String[] args) {

        List<Integer> list = Lists.newArrayList(6, 5, 4, 3, 2, 1);

        for (Integer item : list) {
            ListenableFuture<Integer> future = service.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    TimeUnit.SECONDS.sleep(item);
                    return item;
                }
            });

            Futures.addCallback(future, new FutureCallback<Integer>() {

                @Override
                public void onSuccess(Integer result) {
                    System.out.println("------");
                    System.out.println(item);
                    System.out.println(result);
                    System.out.println("------");
                }

                @Override
                public void onFailure(Throwable t) {
                    t.printStackTrace();
                }
            });
        }

        service.shutdown();

    }

}
