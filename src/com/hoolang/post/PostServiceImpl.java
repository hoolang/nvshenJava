package com.hoolang.post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hoolang.comment.CommentsDao;
import com.hoolang.entity.Comments;
import com.hoolang.entity.Like;
import com.hoolang.entity.Post;
import com.hoolang.like.LikeDao;
import com.hoolang.user.UserDao;
import com.hoolang.util.Hoolang;

@Service("postService")
public class PostServiceImpl implements PostService {

	@Resource
	private PostDao postDao;
	@Resource
	private LikeDao likeDao;
	@Resource
	private UserDao userDao;
	@Resource
	private CommentsDao commentsDao;
	@Override
	public void save(Post post) {
		// TODO Auto-generated method stub
		postDao.save(post);
	}
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" } )
	public List listPosts() {
		List<Post> list = postDao.findByHQL("from Post order by pid desc",  1);
		//List<Post> list = postDao.findBySQL("select pid, content, date , photo , user from HL_POST p", Post.class);
		
		List result = new ArrayList();
		//循环为每条show查询likezong人数，评论总人数
        for(Post post : list){
        	HashMap posts = new HashMap();
        	posts.put("posts", post);
        	posts.put("likes_count", likeDao.findCountByID(Like.class, " where pid = " + post.getPid()));
        	posts.put("comments_count", commentsDao.findCountByID(Comments.class, " where pid = " + post.getPid()));
        	result.add(posts);
        }
		return result;
	}
	
	/**
	 * 首页posts
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" } )
	public HashMap MapPosts(String status, int index, int max){
		// 默认为加载更多show数据
		String SQL = "from Post where pid < " + index + "order by pid desc";
		
		if(status.equalsIgnoreCase(Hoolang.LATEST)){
			// 如果状态为加载最新的show数据就执行这句
			SQL = "from Post where pid > " + index + "order by pid desc";
		}

		List<Post> list = postDao.findByHQL(SQL, max);
		
		List result = new ArrayList();
		
		HashMap _result = new HashMap();
		// 循环为每条show查询likezong人数，评论总人数
        for(Post post : list){
        	HashMap posts = new HashMap();
   	
        	posts.put("posts", post);
        	
        	// 添加喜欢数
        	posts.put("likes_count", likeDao.findCountByID(Like.class, 
        			" where pid = " + post.getPid()));
        	
        	// 添加评论数
        	posts.put("comments_count", commentsDao.findCountByID(Comments.class, 
        			" where pid = " + post.getPid()));
        	result.add(posts);
        	
        }
        
        // 添加一个大的对象，名字为status
        _result.put("status", result);
        
		return _result;
	}
	/**
	 * 
	 */
	@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
	@Override
	public HashMap topPosts(String status, int index, int max) {
		
		HashMap top = new HashMap();
		
		List<Post> latestUsersPosts = postDao.topPosts(status, index, max);
		// 保存转化key后的list
		List _convertList = new ArrayList();
		
		/*
		 * 为结果添加posts键名
		 */
		for(Post posts : latestUsersPosts){
        	HashMap _userPosts = new HashMap();
        	//posts.setPhoto(Hoolang.SHOW_PHOTO_URL + posts.getPhoto());
        	_userPosts.put("posts", posts);
        	// 添加转化后的结果集
        	_convertList.add(_userPosts);
 
		}
		// 添加key名为latestUserPosts的结果集
		top.put("latestUserPosts", _convertList);
	
		// 这里不能为List添加对象映射
		List mostPosts = commentsDao.mostCommentsPosts(status, index, max);

		// 这个list包含带有key名的结果集
		List _mostPosts = new ArrayList();
		
        for(int i = 0 ; i< mostPosts.size();i++){
        	HashMap newMostCommentsPosts = new HashMap();
        	// 转成obj对象
            Object[]  obj = (Object[]) mostPosts.get(i);
            // 把结果集中的第一个对象转为Post对象
            Post post = (Post) obj[0];
            // 把结果集中的第二个对象获取出来
            long count = (Long) obj[1];
            // 添加post的key名
            newMostCommentsPosts.put("posts", post);
            // 为评论数添加key名
            newMostCommentsPosts.put("comments_count", count);
            // 添加转化后的结果集
            _mostPosts.add(newMostCommentsPosts);
            //newMostCommentsPosts.clear();
        }
        // 添加key名位mostCommentsPosts的评论结果集
        top.put("mostCommentsPosts",_mostPosts);
		
		mostPosts = likeDao.mostLikesPosts(status, index, max);
		// 这个list包含带有key名的结果集
		List _mostLikesPosts = new ArrayList();
        for(int i = 0 ; i< mostPosts.size();i++){
        	HashMap newMostLikesPosts = new HashMap();
        	// 转成obj对象
            Object[]  obj = (Object[]) mostPosts.get(i);
            // 把结果集中的第一个对象转为Post对象
            Post post = (Post) obj[0];
            // 把结果集中的第二个对象获取出来
            long count = (Long) obj[1];
            // 添加post的key名
            newMostLikesPosts.put("posts", post);
            // 为评论数添加key名
            newMostLikesPosts.put("likes_count", count);
            // 添加转化后的结果集
            _mostLikesPosts.add(newMostLikesPosts);
        }
		// 添加key名位mostLikesPosts的结果集
		top.put("mostLikesPosts", _mostLikesPosts);
		return top;
		}
	/**
	 * 最新用户发表的信息
	 */
	@Override
	public HashMap latestUserPosts(String status, int index, int max) {
		HashMap top = new HashMap();

		List<Post> latestUsersPosts = postDao.topPosts(status, index, max);
		// 保存转化key后的list
		List _convertList = new ArrayList();

		/*
		 * 为结果添加posts键名
		 */
		for (Post posts : latestUsersPosts) {
			HashMap _userPosts = new HashMap();
			//posts.setPhoto(Hoolang.SHOW_PHOTO_URL + posts.getPhoto());
			_userPosts.put("posts", posts);
			// 添加转化后的结果集
			_convertList.add(_userPosts);

		}
		// 添加key名为latestUserPosts的结果集
		top.put("latestPosts", _convertList);
		return top;
	}
	/**
	 * 最多点赞
	 */
	@Override
	public HashMap mostLikesPosts(String status, int index, int max) {
		HashMap top = new HashMap();
		List mostPosts = likeDao.mostLikesPosts(status, index, max);
		// 这个list包含带有key名的结果集
		List _mostLikesPosts = new ArrayList();
        for(int i = 0 ; i< mostPosts.size();i++){
        	HashMap newMostLikesPosts = new HashMap();
        	// 转成obj对象
            Object[]  obj = (Object[]) mostPosts.get(i);
            // 把结果集中的第一个对象转为Post对象
            Post post = (Post) obj[0];
            // 把结果集中的第二个对象获取出来
            long count = (Long) obj[1];
            // 添加post的key名
            newMostLikesPosts.put("posts", post);
            // 为评论数添加key名
            newMostLikesPosts.put("likes_count", count);
            // 添加转化后的结果集
            _mostLikesPosts.add(newMostLikesPosts);
        }
		// 添加key名位mostLikesPosts的结果集
		top.put("latestPosts", _mostLikesPosts);
		return top;
	}
	/**
	 * 最多评论
	 */
	@Override
	public HashMap mostCommentPosts(String status, int index, int max) {
		HashMap top = new HashMap();
		/// 这里不能为List添加对象映射
		List mostPosts = commentsDao.mostCommentsPosts(status, index, max);

		// 这个list包含带有key名的结果集
		List _mostPosts = new ArrayList();
		
        for(int i = 0 ; i< mostPosts.size();i++){
        	HashMap newMostCommentsPosts = new HashMap();
        	// 转成obj对象
            Object[]  obj = (Object[]) mostPosts.get(i);
            // 把结果集中的第一个对象转为Post对象
            Post post = (Post) obj[0];
            // 把结果集中的第二个对象获取出来
            long count = (Long) obj[1];
            // 添加post的key名
            newMostCommentsPosts.put("posts", post);
            // 为评论数添加key名
            newMostCommentsPosts.put("comments_count", count);
            // 添加转化后的结果集
            _mostPosts.add(newMostCommentsPosts);
            //newMostCommentsPosts.clear();
        }
        // 添加key名位mostCommentsPosts的评论结果集
        top.put("latestPosts",_mostPosts);
		return top;
	}
}
