package com.shuyun.wechatbelle.node.service.impl;

import com.shuyun.wechatbelle.node.domain.Guide;
import com.shuyun.wechatbelle.node.repository.GuideRepository;
import com.shuyun.wechatbelle.node.service.GuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yangtao on 15/7/7.
 */
@Service
public class GuideServiceImpl implements GuideService {

    @Autowired
    private GuideRepository guideRepository;

    @Override
    public List<Guide> findAll() {
        return guideRepository.findAll();
    }

    @Override
    public Guide findById(Long id) {
        return guideRepository.findById(id);
    }

    @Override
    public void add(Guide guide) {
        guideRepository.add(guide);
    }
}
