package com.tyq.eshop.inventory.mapper;

import com.tyq.eshop.inventory.model.ProductInventory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author tangyingqi
 * @date 2019-09-09
 * 库存数量 DAO
 */
@Mapper
public interface ProductInventoryMapper {

    ProductInventory queryProductInventory(@Param("productId") Long productId);

    void updateProductInventory(ProductInventory productInventory);
}
