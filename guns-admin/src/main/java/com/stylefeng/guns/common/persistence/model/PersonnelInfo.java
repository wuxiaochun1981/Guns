package com.stylefeng.guns.common.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * <p>
 * 人员信息
 * </p>
 *
 * @author stylefeng
 * @since 2017-12-29
 */
@TableName("t_personnel_info")
public class PersonnelInfo extends Model<PersonnelInfo> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 人员名称
     */
	@TableField("user_name")
	private String userName;
    /**
     * 性别  1 女  0 男
     */
	private Integer sex;
    /**
     * 年龄
     */
	private Integer age;
    /**
     * 出生日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthday;
    /**
     * 民族
     */
	private String ethnicity;
    /**
     * 国籍
     */
	private String nationality;
    /**
     * 受教育程度
     */
	private String education;
    /**
     * 政治面貌
     */
	private String political;
    /**
     * 户籍类别
     */
	@TableField("household_type")
	private String householdType;
    /**
     * 婚姻状况
     */
	private Integer marriage;
    /**
     * 身份证号
     */
	@TableField("id_card")
	private String idCard;
    /**
     * 手机号
     */
	private String phone;
    /**
     * E-mail 
     */
	private String email;
    /**
     * 就职公司
     */
	private String company;
    /**
     * 省
     */
	private String province;
    /**
     * 职业
     */
	private String occupation;
    /**
     * 市
     */
	private String city;
    /**
     * 区、县
     */
	private String county;
    /**
     * 地址信息
     */
	private String address;
    /**
     * 是否户主 0 否 1 是
     */
	private Integer householder;
    /**
     * 与户主关系
     */
	@TableField("householder_rel")
	private String householderRel;
    /**
     * 户籍省
     */
	@TableField("src_province")
	private String srcProvince;
    /**
     * 户籍城市
     */
	@TableField("src_city")
	private String srcCity;
    /**
     * 户籍 区县
     */
	@TableField("src_county")
	private String srcCounty;
    /**
     * 户籍地址
     */
	@TableField("src_address")
	private String srcAddress;


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

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getEthnicity() {
		return ethnicity;
	}

	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getPolitical() {
		return political;
	}

	public void setPolitical(String political) {
		this.political = political;
	}

	public String getHouseholdType() {
		return householdType;
	}

	public void setHouseholdType(String householdType) {
		this.householdType = householdType;
	}

	public Integer getMarriage() {
		return marriage;
	}

	public void setMarriage(Integer marriage) {
		this.marriage = marriage;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getHouseholder() {
		return householder;
	}

	public void setHouseholder(Integer householder) {
		this.householder = householder;
	}

	public String getHouseholderRel() {
		return householderRel;
	}

	public void setHouseholderRel(String householderRel) {
		this.householderRel = householderRel;
	}

	public String getSrcProvince() {
		return srcProvince;
	}

	public void setSrcProvince(String srcProvince) {
		this.srcProvince = srcProvince;
	}

	public String getSrcCity() {
		return srcCity;
	}

	public void setSrcCity(String srcCity) {
		this.srcCity = srcCity;
	}

	public String getSrcCounty() {
		return srcCounty;
	}

	public void setSrcCounty(String srcCounty) {
		this.srcCounty = srcCounty;
	}

	public String getSrcAddress() {
		return srcAddress;
	}

	public void setSrcAddress(String srcAddress) {
		this.srcAddress = srcAddress;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "PersonnelInfo{" +
			"id=" + id +
			", userName=" + userName +
			", sex=" + sex +
			", age=" + age +
			", birthday=" + birthday +
			", ethnicity=" + ethnicity +
			", nationality=" + nationality +
			", education=" + education +
			", political=" + political +
			", householdType=" + householdType +
			", marriage=" + marriage +
			", idCard=" + idCard +
			", phone=" + phone +
			", email=" + email +
			", company=" + company +
			", province=" + province +
			", occupation=" + occupation +
			", city=" + city +
			", county=" + county +
			", address=" + address +
			", householder=" + householder +
			", householderRel=" + householderRel +
			", srcProvince=" + srcProvince +
			", srcCity=" + srcCity +
			", srcCounty=" + srcCounty +
			", srcAddress=" + srcAddress +
			"}";
	}
}
