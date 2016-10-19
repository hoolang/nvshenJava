package com.hoolang.like;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.hoolang.entity.Like;
import com.hoolang.entity.Post;
import com.hoolang.entity.User;
import com.hoolang.util.JsonTool;
import com.opensymphony.xwork2.ActionSupport;

@Controller
public class LikeAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long lid;
	private User user;
	private Post post;
	private Date likeDate;
	private long pid;
	private long uid;
	private Like like;
	@Resource
	private LikeService likeService;
	
	public String save() throws IOException{

		//此时Like还没有初始化
		like = new Like();
		like.setPost(post);
		like.setUser(user);
		like.setLikeDate(new Date());
		Map status = likeService.save(like,post.getPid(), user.getUsername());

//		JSONArray jsonArray = JSONArray.fromObject(like);  
//        HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);  
//        response.setCharacterEncoding("UTF-8");   
//        response.getWriter().print(jsonArray);
		String params[] = {};
        JsonTool.fromObject(status, params);

		return null;
	}

	public long getLid() {
		return lid;
	}

	public void setLid(long lid) {
		this.lid = lid;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

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

	public long getPid() {
		return pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public Like getLike() {
		return like;
	}

	public void setLike(Like like) {
		this.like = like;
	}

	public LikeService getLikeService() {
		return likeService;
	}

	public void setLikeService(LikeService likeService) {
		this.likeService = likeService;
	}
	

}
