package com.boman.upms.dao.model;

import java.io.Serializable;

public class MessageText implements Serializable {
    /**
     * 编号
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     * 0/1/2广播主题点对点
     *
     * @mbg.generated
     */
    private String type;

    /**
     * 内容
     *
     * @mbg.generated
     */
    private String message;

    /**
     * 标题
     *
     * @mbg.generated
     */
    private String tittle;

    /**
     * 0代表系统
     *
     * @mbg.generated
     */
    private Integer sendId;

    /**
     * 0代表所有用户
     *
     * @mbg.generated
     */
    private String groupIds;

    private Long ctime;
    
    /**
     * 发送人
     */
    private String sendName;
    
    private Integer rectId;
    
    private String rectName;
    
    private String status;
    
    private String urlType;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public Integer getSendId() {
        return sendId;
    }

    public void setSendId(Integer sendId) {
        this.sendId = sendId;
    }

    public String getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(String groupIds) {
        this.groupIds = groupIds;
    }

    public Long getCtime() {
        return ctime;
    }

    public void setCtime(Long ctime) {
        this.ctime = ctime;
    }
    
    public String getSendName() {
		return sendName;
	}

	public void setSendName(String sendName) {
		this.sendName = sendName;
	}

	public Integer getRectId() {
		return rectId;
	}

	public void setRectId(Integer rectId) {
		this.rectId = rectId;
	}

	public String getRectName() {
		return rectName;
	}

	public void setRectName(String rectName) {
		this.rectName = rectName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getUrlType() {
		return urlType;
	}

	public void setUrlType(String urlType) {
		this.urlType = urlType;
	}

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", type=").append(type);
        sb.append(", message=").append(message);
        sb.append(", tittle=").append(tittle);
        sb.append(", sendId=").append(sendId);
        sb.append(", groupIds=").append(groupIds);
        sb.append(", ctime=").append(ctime);
        sb.append("]");
        return sb.toString();
    }
}