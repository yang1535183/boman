package com.boman.cj.dao.model;

import com.boman.upms.dao.model.UpmsUser;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    private Float faceValue;		// 面值
    private String coopBusiness;		// 合作商家
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;		// 有效时间起
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;		// 有效时间止
    private Date receiveDate;		// 领取时间
    private String receive;		// 是否领取
    private UpmsUser receiver;		// 领取人
    private String receiverUserIds;     // 领取人id
    private String receiverUserNames; // 领取人姓名
    private String special;        // 是否专用卷
    private Date beginCreateDate;		// 开始 创建时间
    private Date endCreateDate;		// 结束 创建时间
    private Date beginReceiveDate;		// 开始 领取时间
    private Date endReceiveDate;		// 结束 领取时间
    private String sort;
    private String order;

    private int pageIndex; // 页码

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    private int pageSize; // 页面大小

    public MealRoll() {
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

    public Float getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(Float faceValue) {
        this.faceValue = faceValue;
    }

    public String getCoopBusiness() {
        return coopBusiness;
    }

    public void setCoopBusiness(String coopBusiness) {
        this.coopBusiness = coopBusiness;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
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

    public String getReceiverUserIds() {
        return receiverUserIds;
    }

    public void setReceiverUserIds(String receiverUserIds) {
        this.receiverUserIds = receiverUserIds;
    }

    public String getReceiverUserNames() {
        return receiverUserNames;
    }

    public void setReceiverUserNames(String receiverUserNames) {
        this.receiverUserNames = receiverUserNames;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
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

    public Date getBeginReceiveDate() {
        return beginReceiveDate;
    }

    public void setBeginReceiveDate(Date beginReceiveDate) {
        this.beginReceiveDate = beginReceiveDate;
    }

    public Date getEndReceiveDate() {
        return endReceiveDate;
    }

    public void setEndReceiveDate(Date endReceiveDate) {
        this.endReceiveDate = endReceiveDate;
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