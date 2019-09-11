package com.tyq.eshop.inventory.thread;

import com.tyq.eshop.inventory.request.ProductInventoryCacheRefreshRequest;
import com.tyq.eshop.inventory.request.ProductInventoryDBUpdateRequest;
import com.tyq.eshop.inventory.request.Request;
import com.tyq.eshop.inventory.request.RequestQueue;

import java.util.Map;
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
            if (!request.isForceRefresh()) {
                RequestQueue requestQueue = RequestQueue.getInstance();
                Map<Long, Boolean> flagMap = requestQueue.getFlagMap();
                // 用一个标志位 map 来做查询请求的去重，如果已经有查询请求再排队了，并且后面没有更新请求，那么就过滤掉重复的查询请求
                // 放在这里过滤是因为这里是线程安全的
                if (request instanceof ProductInventoryDBUpdateRequest) {
                    flagMap.put(request.getProductId(), true);
                } else if (request instanceof ProductInventoryCacheRefreshRequest) {
                    Boolean flag = flagMap.get(request.getProductId());

                    // flag 是空说明前面没有请求
                    if (flag == null){
                        flagMap.put(request.getProductId(),false);
                    }

                    // flag 不为空并且是 true 说明前面有更新请求
                    if (flag != null && flag){
                        flagMap.put(request.getProductId(),false);
                    }

                    // 说明前面有查询请求，直接过滤掉
                    if (flag != null && !flag){
                        continue;
                    }

                }
            }
            request.process();
        }
    }
}
