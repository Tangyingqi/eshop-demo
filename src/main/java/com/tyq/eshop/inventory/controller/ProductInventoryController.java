package com.tyq.eshop.inventory.controller;

import com.tyq.eshop.inventory.model.ProductInventory;
import com.tyq.eshop.inventory.request.ProductInventoryCacheRefreshRequest;
import com.tyq.eshop.inventory.request.ProductInventoryDBUpdateRequest;
import com.tyq.eshop.inventory.request.Request;
import com.tyq.eshop.inventory.service.ProductInventoryService;
import com.tyq.eshop.inventory.service.RequestAsynProcessService;
import com.tyq.eshop.inventory.vo.Response;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author tangyingqi
 * @date 2019-09-10
 */
@RestController
public class ProductInventoryController {

    @Resource
    private ProductInventoryService productInventoryService;

    @Resource
    private RequestAsynProcessService requestAsynProcessService;

    @PutMapping("/update-product-inventory")
    public Response updateProductInventory(@RequestBody ProductInventory productInventory){

        Request updateRequest = new ProductInventoryDBUpdateRequest(productInventory,productInventoryService);
        try {
            requestAsynProcessService.process(updateRequest);
            return new Response(Response.SUCCESS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Response(Response.FAILURE);
    }

    @GetMapping("/query-product-inventory")
    public ProductInventory queryProductInventory(@RequestParam("productId") Long productId){

        ProductInventoryCacheRefreshRequest refreshRequest = new ProductInventoryCacheRefreshRequest(productId,productInventoryService);
        try {
            requestAsynProcessService.process(refreshRequest);

            long startTime = System.currentTimeMillis();
            long endTime = 0L;
            long waitTime = 0L;
            ProductInventory productInventory;
            while (true){

                if (waitTime > 200){
                    break;
                }
                 productInventory = productInventoryService.queryProductInventoryFromCache(productId);
                if (productInventory != null){
                    return productInventory;
                }else{
                    Thread.sleep(20);
                    endTime = System.currentTimeMillis();
                    waitTime = endTime - startTime;
                }
            }

            return productInventoryService.queryProductInventory(productId);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return new ProductInventory(productId,-1L);
    }

}
