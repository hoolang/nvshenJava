package com.hoolang.comment;

import java.util.HashMap;

import com.hoolang.entity.Comments;

public interface CommentsService {
	
	public void save(Comments comment);
	/**
	 * 根据pid来查找一个show的评论
	 * @param pid
	 * @return
	 */
	public HashMap findCommentsByPid(String status, long pid, int index, int max);
}
