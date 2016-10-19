package com.hoolang.comment;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import org.springframework.stereotype.Controller;

import com.hoolang.entity.Comments;
import com.hoolang.entity.Post;
import com.hoolang.entity.User;
import com.hoolang.util.Hoolang;
import com.hoolang.util.JsonTool;
import com.opensymphony.xwork2.ActionSupport;

@Controller
public class CommentsAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//评论id
	private long cid;
	private long pid;
	//评论人的ID
	private User user;
	//评论的postID
	private Post post;
	//评论内容
	private String comment;
	//评论日期
	private Date commentDate;
	
	//评论实例
	private Comments comments;
	private CommentsService commentsService;
	
	private int maxid; //从ios传过来的，hibernate不支持long类型的初始结果 query.setFirstResult(index); 以后可能会报错
	private int minid; //同上
	/**
	 * 保存评论
	 * @return
	 * @throws IOException
	 */
	public String save() throws IOException{
		HashMap status = new HashMap();
		if(user ==  null || post == null) {
			status.put(Hoolang.ERROR, Hoolang.WRONG_REQUEST);
			String str [] = {};
			JsonTool.fromObject(status, str);
		}else{
			comments.setCommentDate(new Date());
			comments.setPost(post);
			comments.setUser(user);
			//System.out.println(comments.getComment());
			commentsService.save(comments);

			status.put(Hoolang.SUCCESS, Hoolang.DONE);
			String str [] = {};
			JsonTool.fromObject(status, str);
		}

		return null;
	}
	

	public String findComments(String status, long pid, int cid, int max) throws IOException {
		HashMap hashMap = new HashMap();
//		if(user ==  null || post == null) {
//			status.put(Hoolang.ERROR, Hoolang.WRONG_REQUEST);
//			String str [] = {};
//			JsonTool.fromObject(status, str);
//		}else{
//			comments.setCommentDate(new Date());
//			comments.setPost(post);
//			comments.setUser(user);
//
//			commentsService.save(comments);
//
//			status.put(Hoolang.SUCCESS, Hoolang.DONE);
//			String str [] = {};
//			JsonTool.fromObject(status, str);
//		}

		//String status, int pid, int max
		

		hashMap = commentsService.findCommentsByPid(status, pid, cid, max);
		String str [] = {"post","updateTime"};
		JsonTool.fromObject(hashMap, str);
		return null;
	}
	
	/**
	 * 查找所有的POSTs
	 * @return
	 * @throws IOException
	 */
	public String latest_comments() throws IOException{
		System.out.println("latest_comments->>");
		int max = 3;
		int cid = this.getMaxid();
        
		findComments("latest", pid, cid, max);
		return null;
	}
	
	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	public String older_comments() throws IOException{
		//List list = postService.listPosts();
		System.out.println("older_comments->>");
		int max = 3;
		int index = this.getMinid();
		findComments("older", pid, index, max);
		
		return null;
	}

	public Comments getComments() {
		return comments;
	}
	public void setComments(Comments comments) {
		this.comments = comments;
	}

	public CommentsService getCommentsService() {
		return commentsService;
	}

	public void setCommentsService(CommentsService commentsService) {
		this.commentsService = commentsService;
	}

	public long getCid() {
		return cid;
	}
	public void setCid(long cid) {
		this.cid = cid;
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
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}
	public long getPid() {
		return pid;
	}
	public void setPid(long pid) {
		this.pid = pid;
	}
	public int getMaxid() {
		return maxid;
	}
	public void setMaxid(int maxid) {
		this.maxid = maxid;
	}
	public int getMinid() {
		return minid;
	}
	public void setMinid(int minid) {
		this.minid = minid;
	}
}
