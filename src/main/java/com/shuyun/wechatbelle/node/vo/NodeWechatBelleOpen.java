package com.shuyun.wechatbelle.node.vo;

import java.io.Serializable;

/**
 * Created by shuyun on 2017/7/26.
 */
public class NodeWechatBelleOpen implements Serializable {
    private Long id;
    private String name;
    private String remark;
    private String brandCode;   //品牌编码
    private String materialCode;    //素材编码
    private String materialSort;    //子素材序号 不传查全部子素材
    private String materialId;  //素材标题
    private String materialTitle;   //素材编码
    private BlacklistVo blacklist = new BlacklistVo();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getMaterialSort() {
        return materialSort;
    }

    public void setMaterialSort(String materialSort) {
        this.materialSort = materialSort;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public String getMaterialTitle() {
        return materialTitle;
    }

    public void setMaterialTitle(String materialTitle) {
        this.materialTitle = materialTitle;
    }

    public BlacklistVo getBlacklist() {
        return blacklist;
    }

    public void setBlacklist(BlacklistVo blacklist) {
        this.blacklist = blacklist;
    }
}
