package com.ddl.guava;

import com.google.common.base.Joiner;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * description: 该文件说明
 * @author dongdongliu
 * @version 1.0
 * @date 2018-08-30 15:40:36
 */
public class ConcurrencyTest {

    @Test
    public void test() {
        Futures.addCallback(new ListenableFuture<Object>() {
            @Override
            public void addListener(Runnable listener, Executor executor) {
            }

            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                return false;
            }

            @Override
            public boolean isCancelled() {
                return false;
            }

            @Override
            public boolean isDone() {
                return false;
            }

            @Override
            public Object get() throws InterruptedException, ExecutionException {
                return null;
            }

            @Override
            public Object get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                return null;
            }
        }, new FutureCallback<Object>() {
            @Override
            public void onSuccess(Object result) {

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));

        ListenableFuture explosion = service.submit(new Callable() {
            public Object call() {
                return null;
            }
        });
        Futures.addCallback(explosion, new FutureCallback() {
            // we want this handler to run immediately after we push the big red button!
            public void onSuccess(Object result) {
            }

            public void onFailure(Throwable thrown) {
            }
        });
    }

    @Test
    public void stringTest(){
        Joiner joiner = Joiner.on(";").useForNull("aa");
        String join = joiner.join("Harry", null, "Ron", "Hermione");
        System.out.println(join);

    }
}
