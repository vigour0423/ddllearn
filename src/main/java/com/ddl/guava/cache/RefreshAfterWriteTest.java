package com.ddl.guava.cache;


import com.google.common.base.Stopwatch;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author dongdongliu
 * @date 2018-10-16 10:37:05
 * 使用refreshAfterWrite可以做到：只阻塞加载数据的线程，其余线程返回旧数据。
 * 由于缓存没有数据，导致一个线程去加载数据的时候，别的线程都阻塞了(因为没有旧值可以返回)。
 * 所以一般系统启动的时候，我们需要将数据预先加载到缓存，不然就会出现这种情况。
 */
public class RefreshAfterWriteTest {
    private static CountDownLatch latch = new CountDownLatch(1);

 /*   private static ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool
            (10));*/

    private static Callable<String> callable = () -> {
        // 模拟一个需要耗时2s的数据库查询任务
        System.out.println("begin to mock query db...");
        TimeUnit.SECONDS.sleep(2);
        System.out.println("success to mock query db...");
        return UUID.randomUUID().toString();
    };

    private static LoadingCache<String, String> cache = CacheBuilder
            .newBuilder()
            .refreshAfterWrite(1, TimeUnit.SECONDS)
            .build(new CacheLoader<>() {
                @Override
                public String load(String key) throws Exception {
                    return callable.call();
                }

                /*
                 *真正加载数据的那个线程一定会阻塞，我们希望这个加载过程是异步的。这样就可以让所有线程立马返回旧值，
                 *在后台刷新缓存数据。refreshAfterWrite默认的刷新是同步的，会在调用者的线程中执行。
                 *我们可以改造成异步的，实现CacheLoader.reload()。
                 */
               /* @Override
                public ListenableFuture<String> reload(String key, String oldValue) {
                    System.out.println("......后台线程池异步刷新:" + key);
                    return service.submit(callable);
                }*/
            });

    public static void main(String[] args) throws Exception {

        // 手动添加一条缓存数据,睡眠1.5s让其过期
        cache.put("name", "aty");
        TimeUnit.MILLISECONDS.sleep(1500);
        for (int i = 0; i < 8; i++) {
            startThread(i);
        }

        // 让线程运行
        latch.countDown();

    }

    private static void startThread(int id) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName() + "...begin");
                    latch.await();
                    Stopwatch watch = Stopwatch.createStarted();
                    System.out.println(Thread.currentThread().getName() + "...value..." + cache.get("name"));
                    watch.stop();
                    System.out.println(Thread.currentThread().getName() + "...finish,cost time=" + watch.elapsed(TimeUnit.SECONDS));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        t.setName("Thread-" + id);
        t.start();
    }


}