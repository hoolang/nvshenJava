package com.hoolang.post;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hoolang.dao.base.BaseDaoImpl;
import com.hoolang.entity.Post;
import com.hoolang.util.Hoolang;


@Repository("postDao")
public class PostDaoImpl extends BaseDaoImpl<Post> implements PostDao {

	@Override
	public List<Post> latestUserPosts(String status, int index, int max) {
		// 默认为加载更多show数据
				String SQL = "from Post post where post.pid in"
						+ "( select max(p.pid) as pid from Post p "
						+ "where p.pid < " + index 
								+ " and p.user in "
								+ "(select u.username from User u order by u.uid desc) "
								+ "group by p.user) "
								+ "order by post.pid desc";
				
				if(status.equalsIgnoreCase(Hoolang.LATEST)){
					// 如果状态为加载最新的show数据就执行这句
					SQL = "from Post post where post.pid in"
							+ "( select max(p.pid) as pid from Post p "
							+ "where p.pid > " + index 
							+ " and p.user in (select u.username from User u order by u.uid desc) "
							+ "group by p.user) order by post.pid desc";
				}

		return findByHQL(SQL, max);
	}


	public List<Post> topPosts(String status, int index, int max) {
		// 默认为加载更多show数据
		String SQL = "from Post post where post.pid in"
				+ "( select max(p.pid) as pid from Post p "
				+ "where p.pid < " + index 
						+ " and p.user in "
						+ "(select u.username from User u order by u.uid desc) "
						+ "group by p.user) "
						+ "order by post.pid desc";
		
		if(status.equalsIgnoreCase(Hoolang.LATEST)){
			// 如果状态为加载最新的show数据就执行这句
			SQL = "from Post post where post.pid in"
					+ "( select max(p.pid) as pid from Post p "
					+ "where p.pid > " + index 
					+ " and p.user in (select u.username from User u order by u.uid desc) "
					+ "group by p.user) order by post.pid desc";
		}

		return findByHQL(SQL, max);
	}

}