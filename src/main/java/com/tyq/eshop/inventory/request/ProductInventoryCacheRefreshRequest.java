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

    public ProductInventoryCacheRefreshRequest(Long productId, ProductInventoryService productInventoryService) {
        this.productId = productId;
        this.productInventoryService = productInventoryService;
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
}
