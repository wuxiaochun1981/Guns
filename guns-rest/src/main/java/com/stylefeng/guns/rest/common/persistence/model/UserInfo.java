package com.stylefeng.guns.rest.common.persistence.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldStrategy;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

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
//	@TableField(value = "access_count",update = "%s + 1")
	@TableField(value = "access_count")
	private Integer accessCount;
    /**
     * 失败访问次数
     */
//	@TableField(value = "fail_count",update = "%s + 1")
	@TableField(value = "fail_count")
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
