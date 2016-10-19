package com.hoolang.like;

import java.util.List;

import com.hoolang.dao.base.BaseDao;
import com.hoolang.entity.Like;
import com.hoolang.entity.Post;

public interface LikeDao extends BaseDao<Like> {

	//List<Like> findBySQL(String SQL);
	/**
	 * 获取点赞人数最多的数据
	 * @param status
	 * @param index
	 * @param max
	 * @return
	 */
	List<Like> mostLikesPosts(String status, int index, int max);
	
	List countByUser(String name);
}
