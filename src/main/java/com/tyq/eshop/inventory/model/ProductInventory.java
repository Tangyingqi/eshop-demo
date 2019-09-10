package com.tyq.eshop.inventory.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author tangyingqi
 * @date 2019-09-09
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductInventory {

    private Long productId;

    private Long inventoryCnt;
}
