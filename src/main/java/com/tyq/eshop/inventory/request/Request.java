package com.tyq.eshop.inventory.request;

/**
 * @author tangyingqi
 * @date 2019-09-09
 */
public interface Request {

    void process();

    Long getProductId();

    boolean isForceRefresh();


}
