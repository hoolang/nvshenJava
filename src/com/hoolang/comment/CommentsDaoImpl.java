package com.hoolang.comment;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.hoolang.dao.base.BaseDaoImpl;
import com.hoolang.entity.Comments;
import com.hoolang.entity.Post;

@Repository("commentsDao")
public class CommentsDaoImpl extends BaseDaoImpl<Comments> implements CommentsDao {
	@Override
	public List<Comments> mostCommentsPosts(String status, int index, int max) {
		String SQL = "select c.post, count(c.post) as cpost from Comments c group by c.post order by cpost desc";// group by cpost order by Post";
		return findByHQL(SQL, max);
	}
}