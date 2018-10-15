package com.ddl.guava.cache;

import com.ddl.guava.entity.Student;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author dongdongliu
 * @version 1.0
 */
public class CacheLoaderTest {

    /**
     * cacheLoader方式实现实例：
     */
    @Test
    public void testLoadingCache() throws Exception {

        LoadingCache<Integer, Student> studentCache = CacheBuilder
                .newBuilder()
                //设置并发级别为8，并发级别是指可以同时写缓存的线程数
                .concurrencyLevel(8)
                //设置写缓存后8秒钟过期
                .expireAfterWrite(8, TimeUnit.SECONDS)
                //设置缓存容器的初始容量为10
                .initialCapacity(10)
                //设置缓存最大容量为100，超过100之后就会按照LRU最近最少使用算法来移除缓存项
                .maximumSize(100)
                //开启 Guava Cache 的统计功能
                .recordStats()
                //为缓存增加自动定时刷新功能(存项只有在被检索时才会真正刷)
                /*.refreshAfterWrite(6,TimeUnit.SECONDS)*/
                //设置缓存的移除通知,监听(异步)
                /*   .removalListener(
                           RemovalListeners.asynchronous(notification ->
                                           System.out.println(notification.getKey() + " was removed, cause is " + notification.getCause()),
                                   Executors.newCachedThreadPool())
                   )*/
                //同步监听
                .removalListener(notification ->
                        System.out.println(notification.getKey() + " was removed, cause is " + notification.getCause())
                )
                //build方法中可以指定CacheLoader，在缓存不存在时通过CacheLoader的实现自动加载缓存
                .build(
                        new CacheLoader<>() {
                            @Override
                            public Student load(Integer key) throws Exception {
                                System.out.println("load student " + key);
                                Student student = new Student();
                                student.setRollNo(key);
                                student.setFirstName("name " + key);
                                return student;
                            }
                        }
                );


        for (int i = 0; i < 20; i++) {
            //从缓存中得到数据，由于我们没有设置过缓存，所以需要通过CacheLoader加载缓存数据
            Student student = studentCache.get(1);
            System.out.println(student);
            //休眠1秒
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println("cache stats:");
        //最后打印缓存的命中率等 情况
        System.out.println(studentCache.stats().toString());
        System.out.println("===================================");

        //一次获得多个键的缓存值(已存在的)
        ImmutableMap<Integer, Student> allPresent = studentCache.getAllPresent(Lists.newArrayList(1, 2, 3));
        System.out.println("allPresent" + allPresent);

        //一次获得多个键的缓存值(不存在的key会调用Load加载缓存)
        ImmutableMap<Integer, Student> all = studentCache.getAll(Lists.newArrayList(1, 2, 3));
        System.out.println("all" + all);

        //从缓存中移除缓存项
        studentCache.invalidateAll();
        System.out.println(studentCache.get(1));

        System.out.println("=========显示插入数据=========");

        Student student = new Student();
        student.setRollNo(2);
        student.setFirstName("name " + 2);
        studentCache.put(2, student);

        System.out.println(studentCache.get(2));


        studentCache.asMap().putIfAbsent(3, student);

        System.out.println(studentCache.get(3));

        System.out.println("=========刷新数据=========");
        studentCache.refresh(3);
        System.out.println(studentCache.get(3));

        //TimeUnit.SECONDS.sleep(10);


    }

    /**
     * callable callback的实现：
     */
    @Test
    public void testcallableCache() throws Exception {
        Cache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .build();
        String resultVal = cache.get("jerry", () -> "hello " + "jerry" + "!");

        System.out.println("jerry value : " + resultVal);

        resultVal = cache.get("peida", new Callable<String>() {
            @Override
            public String call() {
                return "hello " + "peida" + "!";
            }
        });
        System.out.println("peida value : " + resultVal);
    }

}
