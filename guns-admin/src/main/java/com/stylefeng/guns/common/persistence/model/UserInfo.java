package com.stylefeng.guns.common.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wuxiaochun
 * @since 2018-02-28
 */
@TableName("t_user_info")
public class UserInfo extends Model<UserInfo> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 用户名
     */
	@TableField("user_name")
	private String userName;
    /**
     * 手机号
     */
	private String phone;
    /**
     * 应用id
     */
	private String appid;
    /**
     * 用户key
     */
	@TableField("user_key")
	private String userKey;
    /**
     * 成功访问数量
     */
	@TableField("access_count")
	private Integer accessCount;
    /**
     * 失败访问次数
     */
	@TableField("fail_count")
	private Integer failCount;
    /**
     * 状态 0 正常 1 暂停 2 删除
     */
	private Integer status;
    /**
     * 创建人
     */
	@TableField("create_user")
	private String createUser;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;


	/** 当前时间查询条件成功条数 */
	@TableField(exist=false)
	private Integer currAccessCount;

	/** 当前时间查询条件失败条数 */
	@TableField(exist=false)
	private Integer currFailCount;



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getUserKey() {
		return userKey;
	}

	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}

	public Integer getAccessCount() {
		return accessCount;
	}

	public void setAccessCount(Integer accessCount) {
		this.accessCount = accessCount;
	}

	public Integer getFailCount() {
		return failCount;
	}

	public void setFailCount(Integer failCount) {
		this.failCount = failCount;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getCurrAccessCount() {
		return currAccessCount;
	}

	public void setCurrAccessCount(Integer currAccessCount) {
		this.currAccessCount = currAccessCount;
	}

	public Integer getCurrFailCount() {
		return currFailCount;
	}

	public void setCurrFailCount(Integer currFailCount) {
		this.currFailCount = currFailCount;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "UserInfo{" +
			"id=" + id +
			", userName=" + userName +
			", phone=" + phone +
			", appid=" + appid +
			", userKey=" + userKey +
			", accessCount=" + accessCount +
			", failCount=" + failCount +
			", status=" + status +
			", createUser=" + createUser +
			", createTime=" + createTime +
			"}";
	}


}
