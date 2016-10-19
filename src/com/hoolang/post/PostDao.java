package com.hoolang.post;

import java.util.List;

import com.hoolang.dao.base.BaseDao;
import com.hoolang.entity.Post;

public interface PostDao extends BaseDao<Post> {
	/**
	 * 获取最新的post数据
	 * @param status
	 * @param index
	 * @param max
	 * @return
	 */
	List<Post> latestUserPosts(String status, int index, int max);
	/**
	 * 获取排行榜数据
	 * @param status
	 * @param index
	 * @param max
	 * @return
	 */
	List<Post> topPosts(String status, int index, int max);

}
