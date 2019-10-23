package com.boman.upms.dao.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 餐卷管理Entity
 * @author 杨炜
 * @version 2019-10-18
 */
public class MealRoll implements Serializable {
    private Integer id;
    private String remarks;	// 备注
    private UpmsUser createBy;	// 创建者
    private Date createDate;	// 创建日期
    private UpmsUser updateBy;	// 更新者
    private Date updateDate;	// 更新日期
    private String delFlag; 	// 删除标记（0：正常；1：删除；2：审核）
    private String faceValue;		// 面值
    private String coopBusiness;		// 合作商家
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date sDate;		// 有效时间起
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date eDate;		// 有效时间止
    private Date cDate;		// 领取时间
    private String receive;		// 是否领取
    private UpmsUser receiver;		// 领取人
    private Date beginCreateDate;		// 开始 创建时间
    private Date endCreateDate;		// 结束 创建时间
    private Date beginCDate;		// 开始 领取时间
    private Date endCDate;		// 结束 领取时间
    private String sort;
    private String order;

    public MealRoll() {
    }

    public MealRoll(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public UpmsUser getCreateBy() {
        return createBy;
    }

    public void setCreateBy(UpmsUser createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public UpmsUser getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(UpmsUser updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(String faceValue) {
        this.faceValue = faceValue;
    }

    public String getCoopBusiness() {
        return coopBusiness;
    }

    public void setCoopBusiness(String coopBusiness) {
        this.coopBusiness = coopBusiness;
    }

    public Date getsDate() {
        return sDate;
    }

    public void setsDate(Date sDate) {
        this.sDate = sDate;
    }

    public Date geteDate() {
        return eDate;
    }

    public void seteDate(Date eDate) {
        this.eDate = eDate;
    }

    public Date getcDate() {
        return cDate;
    }

    public void setcDate(Date cDate) {
        this.cDate = cDate;
    }

    public String getReceive() {
        return receive;
    }

    public void setReceive(String receive) {
        this.receive = receive;
    }

    public UpmsUser getReceiver() {
        return receiver;
    }

    public void setReceiver(UpmsUser receiver) {
        this.receiver = receiver;
    }

    public Date getBeginCreateDate() {
        return beginCreateDate;
    }

    public void setBeginCreateDate(Date beginCreateDate) {
        this.beginCreateDate = beginCreateDate;
    }

    public Date getEndCreateDate() {
        return endCreateDate;
    }

    public void setEndCreateDate(Date endCreateDate) {
        this.endCreateDate = endCreateDate;
    }

    public Date getBeginCDate() {
        return beginCDate;
    }

    public void setBeginCDate(Date beginCDate) {
        this.beginCDate = beginCDate;
    }

    public Date getEndCDate() {
        return endCDate;
    }

    public void setEndCDate(Date endCDate) {
        this.endCDate = endCDate;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}