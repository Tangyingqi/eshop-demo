package com.tyq.eshop.inventory.request;

import com.tyq.eshop.inventory.model.ProductInventory;
import com.tyq.eshop.inventory.service.ProductInventoryService;

/**
 * @author tangyingqi
 * @date 2019-09-09]
 * <p>
 * 数据更新的 Request
 * <p>
 * 1.删除缓存
 * 2.更新数据库
 */
public class ProductInventoryDBUpdateRequest implements Request {

    private ProductInventory productInventory;
    private ProductInventoryService productInventoryService;

    public ProductInventoryDBUpdateRequest(ProductInventory productInventory, ProductInventoryService productInventoryService) {
        this.productInventory = productInventory;
        this.productInventoryService = productInventoryService;
    }

    @Override
    public void process() {


        // 删除 Redis 中的缓存
        productInventoryService.deleteProductInventoryFromCache(productInventory);

        // 修改数据库中的数据
        productInventoryService.updateProductInventory(productInventory);

    }

    @Override
    public Long getProductId() {
        return productInventory.getProductId();
    }

    @Override
    public boolean isForceRefresh() {
        return false;
    }


}
