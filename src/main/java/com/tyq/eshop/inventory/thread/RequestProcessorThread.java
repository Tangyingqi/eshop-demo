package com.tyq.eshop.inventory.thread;

import com.tyq.eshop.inventory.request.Request;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;

/**
 * @author tangyingqi
 * @date 2019-09-09
 */
public class RequestProcessorThread implements Callable<Boolean> {

    /**
     * 监控的内存队列
     */
    private ArrayBlockingQueue<Request> queue;

    public RequestProcessorThread(ArrayBlockingQueue<Request> queue) {
        this.queue = queue;
    }

    @Override
    public Boolean call() throws Exception {
        while (true){
            Request request = queue.take();
            request.process();
        }
    }
}
