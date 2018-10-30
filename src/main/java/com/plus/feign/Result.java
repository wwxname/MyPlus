package com.plus.feign;

import lombok.Data;

/**
 * @author plus me
 * @date 2018/10/30
 */
@Data
public class Result {
    public Integer code;
    public String desc;
    public Object data;
    public Boolean smsOk;
}
