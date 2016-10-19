package com.hoolang.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hoolang.entity.User;
import com.hoolang.like.LikeDao;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;
	@Resource
	private LikeDao likeDao;
	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		userDao.save(user);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public HashMap selectByName(String name) {

		List<User> list = listByName(name);
		User user = new User();
		for(int i = 0;i < list.size(); i ++){
			user = list.get(i);
		}
		
		List like = likeDao.countByUser(name);
		String likeCount = "";
		for(int i = 0;i < like.size(); i ++){
			likeCount = like.get(i) + "";
		}
		
		HashMap temp = new HashMap();
		temp.put("user", user);
		System.out.println(likeCount);
		temp.put("like_count", likeCount);
		
		// 用list数组包装map
		List listTemp = new ArrayList();
		listTemp.add(temp);
		
		HashMap result = new HashMap();
		result.put("userinfo", listTemp);
		
		return result;
	}

	@Override
	public List<User> listByName(String name) {
		// TODO Auto-generated method stub
		return userDao.selectByName(name);
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		userDao.update(user);
	}

	@Override
	public void updateNickname(String nickname, int uid) {
		// TODO Auto-generated method stub
		userDao.updateNickname(nickname, uid);
	}

	@Override
	public void updateText(String text, int uid) {
		// TODO Auto-generated method stub
		userDao.updateText(text, uid);
	}

	@Override
	public void updateLocal(String province, String city, int uid) {
		// TODO Auto-generated method stub
		userDao.updateLocal(province, city, uid);
	}

	@Override
	public void updateSex(char sex, int uid) {
		// TODO Auto-generated method stub
		userDao.updateSex(sex, uid);
	}

	@Override
	public HashMap searchNickname(String nickname) {
		// TODO Auto-generated method stub
		List<User> list = userDao.searchNickname(nickname); 
		
		HashMap result = new HashMap();
		result.put("users", list);
		return result;
	}

}
