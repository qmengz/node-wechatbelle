package com.shuyun.wechatbelle.node.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by yangtao on 15/7/7.
 */
@Entity
@Table(name = "guide_entry")
public class Guide implements Serializable {
    private Long id;
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, precision = 10, scale = 0)
    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    @Column(name = "name", length = 100)
    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
