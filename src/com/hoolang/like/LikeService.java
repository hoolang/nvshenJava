package com.hoolang.like;

import java.util.HashMap;
import java.util.List;

import com.hoolang.entity.Like;

public interface LikeService {

	public HashMap save(Like like, long pid, String name);
	
	/**
	 * 根据pid和uid删除点赞数据
	 * @param pid
	 * @param uid
	 */
	public void delete(long pid, String name);
	
	public List find(long pid, String name);

}
