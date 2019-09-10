package com.tyq.eshop.inventory.service;

import com.tyq.eshop.inventory.request.Request;
import com.tyq.eshop.inventory.request.RequestQueue;
import org.springframework.stereotype.Service;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author tangyingqi
 * @date 2019-09-10
 */
@Service
public class RequestAsynProcessService {

    public void process(Request request) throws InterruptedException {

        // 根据 productID 路由到对应的 queue 中
        ArrayBlockingQueue<Request> queue = getRoutingQueue(request.getProductId());
        // 把请求放入 queue 中
        queue.put(request);

    }

    private ArrayBlockingQueue<Request> getRoutingQueue(Long productId) {
        RequestQueue requestQueue = RequestQueue.getInstance();

        // 先获取productId的hash值
        String key = String.valueOf(productId);
        int h;
        int hash = (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);

        int index = (requestQueue.queueSize() - 1) & hash;

        return requestQueue.getQueue(index);
    }
}
