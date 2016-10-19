package com.hoolang.user;

import java.util.HashMap;
import java.util.List;

import com.hoolang.entity.User;

public interface UserService {

	/**
	 * 保存用户
	 * @param user
	 */
	public void save(User user);
	/**
	 * 跟新用户
	 * @param user
	 */
	public void update(User user);
	/**
	 * 更新昵称
	 * @param nickname
	 * @param uid
	 */
	public void updateNickname(String nickname, int uid);
	/**
	 * 更新简介
	 * @param text
	 * @param uid
	 */
	public void updateText(String text, int uid);
	/**
	 * 更新地区
	 * @param province
	 * @param city
	 * @param uid
	 */
	public void updateLocal(String province, String city, int uid);
	/**
	 * 更新性别
	 * @param sex
	 * @param uid
	 */
	public void updateSex(char sex, int uid);
	
	/**
	 * 根据用户名查找用户信息
	 * @param name
	 * @return
	 */
	public HashMap selectByName(String name);
	/**
	 * 查找一个用户的信息
	 * @param name
	 * @return
	 */
	public List<User> listByName(String name);
	/**
	 * 根据昵称模糊查询
	 * @param nickname
	 * @return
	 */
	public HashMap searchNickname(String nickname);
}
