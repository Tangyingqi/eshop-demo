package com.tyq.eshop.inventory.request;

import com.tyq.eshop.inventory.model.ProductInventory;
import com.tyq.eshop.inventory.service.ProductInventoryService;

/**
 * @author tangyingqi
 * @date 2019-09-10
 */
public class ProductInventoryCacheRefreshRequest implements Request {

    private Long productId;
    private ProductInventoryService productInventoryService;
    private boolean isForceRefresh;

    public ProductInventoryCacheRefreshRequest(Long productId, ProductInventoryService productInventoryService,boolean isForceRefresh) {
        this.productId = productId;
        this.productInventoryService = productInventoryService;
        this.isForceRefresh = isForceRefresh;
    }

    @Override
    public void process() {

        // 从数据库中查出数据
        ProductInventory productInventory = productInventoryService.queryProductInventory(this.productId);

        // 数据写入缓存
        productInventoryService.setProductInventoryToCache(productInventory);
    }

    @Override
    public Long getProductId() {
        return productId;
    }

    @Override
    public boolean isForceRefresh() {
        return isForceRefresh;
    }
}
