package com.tyq.eshop.inventory.request;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 请求内存队列
 * @author tangyingqi
 * @date 2019-09-09
 */
public class RequestQueue {

    private List<ArrayBlockingQueue<Request>> queues = new ArrayList<>();
    private Map<Long,Boolean> flagMap = new ConcurrentHashMap<>();

    public int queueSize() {
        return queues.size();
    }

    public ArrayBlockingQueue<Request> getQueue(int index) {
        return queues.get(index);
    }

    private static class Singleton{

        private static RequestQueue instance;

        static {
            instance = new RequestQueue();
        }

    }

    public static RequestQueue getInstance(){
        return Singleton.instance;
    }

    /**
     * 添加一个内存队列
     * @param queue
     */
    public void addQueue(ArrayBlockingQueue<Request> queue){
        this.queues.add(queue);
    }

    public Map<Long, Boolean> getFlagMap() {
        return flagMap;
    }
}
