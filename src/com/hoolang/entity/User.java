package com.hoolang.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "HL_USER")
public class User implements Serializable {

/**
	 * 
	 */
	private static final long serialVersionUID = 1111L;
/**
* 用户ID
用户昵称
头像URL
性别
所在地
简介 
注册时间
修改时间
*/
	private int uid;
	private String username;
	private String password;
	private String nickname;
	private String email;
	private String icon;
	private char sex;
	private String province;
	private String city;
	private String text;
	private int verified_type;
	private Date registerTime;
	private Date updateTime;
	
	public User(){}

	public User(int uid, String username, String password, String nickname,
			String email, String icon, char sex, String province, String city,
			String text, int verified_type, Date registerTime, Date updateTime) {
		super();
		this.uid = uid;
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.email = email;
		this.icon = icon;
		this.sex = sex;
		this.province = province;
		this.city = city;
		this.text = text;
		this.verified_type = verified_type;
		this.registerTime = registerTime;
		this.updateTime = updateTime;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	@Column(nullable = false)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIcon() {
		return icon;
	}


	public void setIcon(String icon) {
		this.icon = icon;
	}


	public char getSex() {
		return sex;
	}


	public void setSex(char sex) {
		this.sex = sex;
	}


	public String getProvince() {
		return province;
	}


	public void setProvince(String province) {
		this.province = province;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public int getVerified_type() {
		return verified_type;
	}


	public void setVerified_type(int verified_type) {
		this.verified_type = verified_type;
	}


	public Date getRegisterTime() {
		return registerTime;
	}


	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}


	public Date getUpdateTime() {
		return updateTime;
	}


	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
