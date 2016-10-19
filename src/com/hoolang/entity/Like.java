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



@Entity
@Table(name="HL_LIKE")
public class Like implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1113L;
	
	private long lid;
	private User user;
	private Post post;
	private Date likeDate;
	
	public Like(){};
	public Like(long lid, User user, Post post, Date likeDate) {
		super();
		this.lid = lid;
		this.user = user;
		this.post = post;
		this.likeDate = likeDate;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getLid() {
		return lid;
	}
	public void setLid(long lid) {
		this.lid = lid;
	}
	@ManyToOne
	@JoinColumn(name="user", referencedColumnName="username")//referencedColumnName指定映射的列
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@ManyToOne
	@JoinColumn(name="pid", referencedColumnName="pid")//referencedColumnName指定映射的列
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	public Date getLikeDate() {
		return likeDate;
	}
	public void setLikeDate(Date likeDate) {
		this.likeDate = likeDate;
	}
	
}
