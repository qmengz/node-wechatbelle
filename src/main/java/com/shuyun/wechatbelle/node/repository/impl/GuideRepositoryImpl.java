package com.shuyun.wechatbelle.node.repository.impl;

import com.shuyun.wechatbelle.node.domain.Guide;
import com.shuyun.wechatbelle.node.repository.GuideRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaMetamodelEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by yangtao on 15/7/7.
 */
@Repository("guideRepository")
@Transactional
public class GuideRepositoryImpl implements GuideRepository {
    @PersistenceContext
    private EntityManager entityManager;

    private SimpleJpaRepository<Guide, Long> guideRepository;

    @PostConstruct
    public void init() {
        final JpaEntityInformation<Guide, Long> campaignEntityInfo = new JpaMetamodelEntityInformation<Guide, Long>(
                Guide.class, entityManager.getMetamodel());
        guideRepository = new SimpleJpaRepository<Guide, Long>(campaignEntityInfo, entityManager);
    }

    @Override
    public List<Guide> findAll() {
        return guideRepository.findAll();
    }

    @Override
    public Guide findById(Long id) {
        return guideRepository.findOne(id);
    }

    @Override
    public void add(Guide guide) {
        guideRepository.saveAndFlush(guide);
    }
}
