package com.tyq.eshop.inventory.service;

import com.tyq.eshop.inventory.dao.RedisDAO;
import com.tyq.eshop.inventory.mapper.ProductInventoryMapper;
import com.tyq.eshop.inventory.model.ProductInventory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author tangyingqi
 * @date 2019-09-10
 */
@Service
public class ProductInventoryService {

    @Resource
    private ProductInventoryMapper productInventoryMapper;

    @Resource
    private RedisDAO redisDAO;

    public ProductInventory queryProductInventory(Long productId){
        return productInventoryMapper.queryProductInventory(productId);
    }

    public ProductInventory queryProductInventoryFromCache(Long productId){

        Long inventoryCnt = 0L;
        String key = "product:inventory:" + productId;
        String result = redisDAO.get(key);
        if (result != null && !"".equals(result)){
            inventoryCnt = Long.valueOf(result);
            return new ProductInventory(productId,inventoryCnt);
        }

        return null;
    }

    public void deleteProductInventoryFromCache(ProductInventory productInventory){

        String key = "product:inventory:" + productInventory.getProductId();
        redisDAO.delete(key);
    }

    public void updateProductInventory(ProductInventory productInventory) {

        productInventoryMapper.updateProductInventory(productInventory);
    }

    public void setProductInventoryToCache(ProductInventory productInventory){

        String key = "product:inventory:" + productInventory.getProductId();
        redisDAO.set(key,String.valueOf(productInventory.getInventoryCnt()));
    }


}
