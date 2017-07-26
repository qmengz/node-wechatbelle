package com.shuyun.wechatbelle.node.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jianai.deng on 2015/9/14.
 */
public class BlacklistVo {
    private List<NamelistVo> customer = new ArrayList<NamelistVo>();

    public List<NamelistVo> getCustomer() {
        return customer;
    }

    public void setCustomer(List<NamelistVo> customer) {
        this.customer = customer;
    }
}
