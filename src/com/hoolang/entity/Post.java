package com.hoolang.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "HL_POST")
public class Post implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1110L;

	/**
	 * 2、内容表 内容ID 用户ID 文字 图片 时间
	 */
	private long pid;
	private User user;
	private String content;
	private String photo;
	private Date created_at;
	
	public Post(){}
	
	public Post(long pid, User user, String content, String photo,
			Date created_at) {
		super();
		this.pid = pid;
		this.user = user;
		this.content = content;
		this.photo = photo;
		this.created_at = created_at;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Type(type="long")
	public long getPid() {
		return pid;
	}
	public void setPid(long pid) {
		this.pid = pid;
	}
	@ManyToOne(targetEntity= User.class)//, fetch = FetchType.LAZY)
	@JoinColumn(name="user", referencedColumnName="username")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

}
