package com.shuyun.wechatbelle.node.vo;

import com.shuyun.wechatbelle.node.domain.Guide;

import java.util.Date;
import java.util.List;

/**
 * Created by yangtao on 15/7/8.
 */
public class GuideListJson {
    private Date request;
    private List<Guide> data;
    private Date response;

    public Date getResponse() {
        return response;
    }

    public void setResponse(Date response) {
        this.response = response;
    }

    public Date getRequest() {
        return request;
    }

    public void setRequest(Date request) {
        this.request = request;
    }

    public List<Guide> getData() {
        return data;
    }

    public void setData(List<Guide> data) {
        this.data = data;
    }
}
