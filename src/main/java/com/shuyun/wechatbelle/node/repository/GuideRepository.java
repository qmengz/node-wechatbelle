package com.shuyun.wechatbelle.node.repository;

import com.shuyun.wechatbelle.node.domain.Guide;

import java.util.List;

/**
 * Created by yangtao on 15/7/7.
 */
public interface GuideRepository {
    List<Guide> findAll();
    Guide findById(Long id);
    void add(Guide guide);
}
