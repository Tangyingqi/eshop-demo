package com.tyq.eshop.inventory.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author tangyingqi
 * @date 2019-09-10
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    public static final String SUCCESS = "success";
    public static final String FAILURE = "failure";

    private String status;
    private String message;

    public Response(String status) {
        this.status = status;
    }
}
