package com.hoolang.comment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hoolang.entity.Comments;
import com.hoolang.entity.Like;
import com.hoolang.entity.Post;
import com.hoolang.util.Hoolang;

@Service("commentsService")
public class CommentsServiceImpl implements CommentsService {

	@Resource
	private CommentsDao commentsDao;
	@Override
	public void save(Comments comment) {
		commentsDao.save(comment);
	}

	@Override
//	public HashMap findCommentsByPid(long pid) {
//		HashMap map = new HashMap();
//		List<Comments> list = commentsDao.findByHQL("from Comments where pid = " + pid);
//		map.put("comments", list);
//		return map;
//	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" } )
	public HashMap findCommentsByPid(String status, long pid, int index, int max){
		// 默认为加载更多show数据
		String SQL = "from Comments where cid < " + index + " and pid = " + pid + " order by cid desc";
		
		if(status.equalsIgnoreCase("latest")){
			// 如果状态为加载最新的show数据就执行这句
			SQL = "from Comments where cid > " + index + " and pid = " + pid + " order by cid desc";
		}

		System.out.println(SQL);
		
		List<Comments> list = commentsDao.findByHQL(SQL, max);
		
		List result = new ArrayList();
		
		HashMap _result = new HashMap();
		// 循环为每条show查询likezong人数，评论总人数
        for(Comments comment : list){
        	HashMap comments = new HashMap();
//        	User user = new User();
//        	user = post.getUser();
//        	
//        	System.out.println("user.getIcon():" + user.getIcon());
//        	// 重新设置一下用户头像的绝对路径
//        	System.out.println(post.getUser().getIcon());
//
//        	post.getUser().setIcon(Hoolang.USER_ICON_URL + post.getUser().getIcon());
//        	System.out.println(post.getUser().getIcon());
        	
        	// 重新设置show图片的绝对路径
        	//comment.setPhoto(Hoolang.SHOW_PHOTO_URL + post.getPhoto());
        	
        	comments.put("posts", comment);
        	
        	// 添加喜欢数
        	//posts.put("likes_count", likeDao.findCountByID(Like.class, " where pid = " + post.getPid()));
        	// 添加评论数
        	//posts.put("comments_count", commentsDao.findCountByID(Comments.class, " where pid = " + post.getPid()));
        	result.add(comments);
        	
        }
        // 添加一个大的对象，名字为status
        _result.put("comments", list);
		return _result;
	}

}
