package com.shuyun.wechatbelle.node.service;

import com.shuyun.wechatbelle.node.domain.Guide;

import java.util.List;

/**
 * Created by yangtao on 15/7/7.
 */
public interface GuideService {
    List<Guide> findAll();
    Guide findById(Long id);
    void add(Guide guide);
}
