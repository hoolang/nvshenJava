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
import javax.persistence.Transient;

@Entity
@Table(name = "HL_COMMENT")
public class Comments implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1112L;
	
	//评论id
	private long cid;
	//评论人的ID
	private User user;
	//评论的postID
	private Post post;
	//评论内容
	private String comment;
	//评论日期
	private Date commentDate;
	// 字段统计
	private int count;
	
	public Comments(){};

	public Comments(long cid, User user, Post post, String comment,
			Date commentDate, int count) {
		super();
		this.cid = cid;
		this.user = user;
		this.post = post;
		this.comment = comment;
		this.commentDate = commentDate;
		this.count = count;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getCid() {
		return cid;
	}
	public String getComment() {
		return comment;
	}
	public Date getCommentDate() {
		return commentDate;
	}
	@ManyToOne
	@JoinColumn(name="pid", referencedColumnName="pid")//referencedColumnName指定映射的列
	public Post getPost() {
		return post;
	}
	@ManyToOne
	@JoinColumn(name="user", referencedColumnName="username")//referencedColumnName指定映射的列
	public User getUser() {
		return user;
	}
	public void setCid(long cid) {
		this.cid = cid;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Transient
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	

}
