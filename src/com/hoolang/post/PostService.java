package com.hoolang.post;

import java.util.HashMap;
import java.util.List;

import com.hoolang.entity.Post;

public interface PostService {

	public void save(Post post);
	
	public List listPosts();
	/**
	 * 首页
	 * @param status
	 * @param index
	 * @param max
	 * @return
	 */
	public HashMap MapPosts(String status, int index, int max);
	/**
	 * 排行榜
	 * @param status
	 * @param index
	 * @param max
	 * @return
	 */
	public HashMap topPosts(String status, int index, int max);
	/**
	 * 最新用户
	 * @param status
	 * @param index
	 * @param max
	 * @return
	 */
	public HashMap latestUserPosts(String status, int index, int max);
	/**
	 * 最多点赞
	 * @param status
	 * @param index
	 * @param max
	 * @return
	 */
	public HashMap mostLikesPosts(String status, int index, int max);
	/**
	 * 最多评论
	 * @param status
	 * @param index
	 * @param max
	 * @return
	 */
	public HashMap mostCommentPosts(String status, int index, int max);
}
