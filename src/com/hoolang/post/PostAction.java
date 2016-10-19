package com.hoolang.post;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.hoolang.entity.Post;
import com.hoolang.entity.User;
import com.hoolang.util.Hoolang;
import com.hoolang.util.JsonTool;
import com.opensymphony.xwork2.ActionSupport;

@Controller
public class PostAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Resource
	private PostService postService;
	private User user;
	private Post post;
	private long pid;
	private String content;
	private String photo;
	private Date created_at;
	private int maxid; //从ios传过来的，hibernate不支持long类型的初始结果 query.setFirstResult(index); 以后可能会报错
	private int minid; //同上

	//代表上传文件的file对象
    private File file;
    //上传文件名
    private String fileFileName;
    //上传文件的MIME类型
    private String fileContentType;
    //保存上传文件的目录
    private String uploadDir = "/photos";
    //新文件名称
    private String newFileName = null;
    //原文件名
    private String fileString;
    
    /***
     * 添加Post
     * @return
     * @throws Exception
     */
	public String save() throws Exception{
		
		upload();
		Date date = new Date();
		System.out.println(user);
		post.setUser(user);
		//System.out.println(user.getUid());
		post.setCreated_at(date);
		post.setPhoto(upload());
		//System.out.println(post.getContent());
		postService.save(post);

		return null;
	}
	/***
	 * 图片上传
	 * @return
	 * @throws IOException
	 */
	public String upload() throws IOException{
        // 得到当前时间自1970年1月1日0时0分0秒开始流逝的毫秒数，将这个毫秒数作为上传文件新的文件名。  
        long now = new Date().getTime();
        // 得到保存上传文件的目录的真实路径 
        String path = ServletActionContext.getServletContext().getRealPath(uploadDir);
        File dir = new File(path);
        // 如果这个目录不存在，则创建它
        if (!dir.exists())
            dir.mkdir();
        int index = fileFileName.lastIndexOf('.');
        // 判断上传文件名是否有扩展名
        if (index != -1)
            newFileName = now + fileFileName.substring(index);
        else
            newFileName = Long.toString(now);
        BufferedOutputStream bos = null;
        BufferedInputStream bis = null;
        // 读取保存在临时目录下的上传文件，写入到新的文件中。
        try {
            FileInputStream fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            FileOutputStream fos = new FileOutputStream(new File(dir,newFileName));
            bos = new BufferedOutputStream(fos);
            byte[] buf = new byte[4096];
            int len = -1;
            while ((len = bis.read(buf)) != -1) {
                bos.write(buf, 0, len);
            }
        } finally {
            try {
                if (null != bis)
                    bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }  
            try {
                if (null != bos)
                    bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Hoolang.SHOW_PHOTO_URL + newFileName;
    } 
	
	/**
	 * 查找最新的POSTs
	 * @return
	 * @throws IOException
	 */
	public String latest_posts() throws IOException{
		System.out.println("latest_posts->>");
		int max = 3;
		int index = this.getMaxid();
        
        listPosts(Hoolang.LATEST, index, max);
		return null;
	}
	
	/**
	 * 获取旧的post数据
	 * @return
	 * @throws IOException
	 */
	public void older_posts() throws IOException{
		//List list = postService.listPosts();
		System.out.println("older_posts->>");
		int max = 3;
		int index = this.getMinid();
		
		listPosts(Hoolang.OLDER, index, max);
	}
	
	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	public String listPosts(String status, int index, int max) throws IOException{
		//List list = postService.listPosts();

		HashMap hashMap = postService.MapPosts(status, index, max);

		//设置要排除显示的字段
		String params[] = {"registerTime","updateTime"};
        JsonTool.fromObject(hashMap, params);
		return null;
	}
	/**
	 * 获取排行榜数据
	 * @param status 	状态： latest 或者 older 表示获取最新数据或者获取旧数据
	 * @param index 	这是一个post pid 根据pid来判断获取哪些数据
	 * @param max 		结果集的最大条数
	 * @return
	 * @throws IOException
	 */
	public String topPosts(String status, int index, int max) throws IOException{
		HashMap hashMap = postService.topPosts(status, index, max);
		String params[] = {"registerTime","updateTime"};
		JsonTool.fromObject(hashMap, params);
		return null;
	}
	
	/**
	 * 获取旧的post数据
	 * @return
	 * @throws IOException
	 */
	public void top_older_posts() throws IOException{
		//List list = postService.listPosts();
		System.out.println("top_older_posts->>");
		int max = 10;
		int index = this.getMinid();
		
		topPosts(Hoolang.OLDER, index, max);
	}
	
	/**
	 * 获取旧的post数据
	 * @return
	 * @throws IOException
	 */
	public void top_latest_posts() throws IOException{
		//List list = postService.listPosts();
		System.out.println("top_latest_posts->>");
		int max = 10;
		int index = this.getMaxid();
		
		topPosts(Hoolang.LATEST, index, max);
	}
	
	/**
	 * 最新用户发表的数据
	 * @param status
	 * @param index
	 * @param max
	 * @return
	 * @throws IOException
	 */
	public String latestUserPosts(String status, int index, int max) throws IOException{
		HashMap hashMap = postService.latestUserPosts(status, index, max);
		String params[] = {"registerTime","updateTime"};
		JsonTool.fromObject(hashMap, params);
		return null;
	}
	/**
	 * 获取旧的post数据
	 * @return
	 * @throws IOException
	 */
	public void top_latest_user_older_posts() throws IOException{
		//List list = postService.listPosts();
		System.out.println("top_latest_user_older_posts->>");
		int max = 10;
		int index = this.getMinid();
		
		latestUserPosts(Hoolang.OLDER, index, max);
	}
	
	/**
	 * 获取旧的post数据
	 * @return
	 * @throws IOException
	 */
	public void top_latest_user_latest_posts() throws IOException{
		//List list = postService.listPosts();
		System.out.println("top_latest_user_latest_posts->>");
		int max = 10;
		int index = this.getMaxid();
		
		latestUserPosts(Hoolang.LATEST, index, max);
	}
	
	/**
	 * 最多喜欢的数据
	 * @param status
	 * @param index
	 * @param max
	 * @return
	 * @throws IOException
	 */
	public String mostLikesPosts(String status, int index, int max) throws IOException{
		HashMap hashMap = postService.mostLikesPosts(status, index, max);
		String params[] = {"registerTime","updateTime"};
		JsonTool.fromObject(hashMap, params);
		return null;
	}
	/**
	 * 获取旧的post数据
	 * @return
	 * @throws IOException
	 */
	public void top_most_likes_older_posts() throws IOException{
		//List list = postService.listPosts();
		System.out.println("top_most_likes_older_posts->>");
		int max = 10;
		int index = this.getMinid();
		
		mostLikesPosts(Hoolang.OLDER, index, max);
	}
	/**
	 * 获取旧的post数据
	 * @return
	 * @throws IOException
	 */
	public void top_most_likes_latest_posts() throws IOException{
		//List list = postService.listPosts();
		System.out.println("top_most_likes_latest_posts->>");
		int max = 10;
		int index = this.getMaxid();
		
		mostLikesPosts(Hoolang.LATEST, index, max);
	}
	
	/**
	 * 最多评论的数据
	 * @param status
	 * @param index
	 * @param max
	 * @return
	 * @throws IOException
	 */
	public String mostCommentPosts(String status, int index, int max) throws IOException{
		HashMap hashMap = postService.mostCommentPosts(status, index, max);
		String params[] = {"registerTime","updateTime"};
		JsonTool.fromObject(hashMap, params);
		return null;
	}
	/**
	 * 获取旧的post数据
	 * @return
	 * @throws IOException
	 */
	public void top_most_comments_older_posts() throws IOException{
		//List list = postService.listPosts();
		System.out.println("top_most_comments_older_posts->>");
		int max = 10;
		int index = this.getMinid();
		
		mostCommentPosts(Hoolang.OLDER, index, max);
	}
	
	/**
	 * 获取旧的post数据
	 * @return
	 * @throws IOException
	 */
	public void top_most_comments_latest_posts() throws IOException{
		//List list = postService.listPosts();
		System.out.println("top_most_comments_latest_posts->>");
		int max = 10;
		int index = this.getMaxid();
		
		mostCommentPosts(Hoolang.LATEST, index, max);
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
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	public PostService getPostService() {
		return postService;
	}
	public void setPostService(PostService postService) {
		this.postService = postService;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public long getPid() {
		return pid;
	}
	public void setPid(long pid) {
		this.pid = pid;
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
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	public String getFileContentType() {
		return fileContentType;
	}
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	public String getUploadDir() {
		return uploadDir;
	}
	public void setUploadDir(String uploadDir) {
		this.uploadDir = uploadDir;
	}
	public String getNewFileName() {
		return newFileName;
	}
	public void setNewFileName(String newFileName) {
		this.newFileName = newFileName;
	}
	public String getFileString() {
		return fileString;
	}
	public void setFileString(String fileString) {
		this.fileString = fileString;
	}
}
