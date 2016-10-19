package com.hoolang.user;

import java.util.List;

import com.hoolang.dao.base.BaseDao;
import com.hoolang.entity.User;

public interface UserDao extends BaseDao<User> {

	/**
	 * 根据Name查找用户信息
	 * @return
	 */
	List<User> selectByName(String name);
	/**
	 * 根据nickname查询用户
	 * @param nickname
	 * @return
	 */
	List<User> searchNickname(String nickname);
	/**
	 * 更新昵称
	 * @param nickname
	 * @param uid
	 */
	void updateNickname(String nickname, int uid);
	/**
	 * 更新描述
	 * @param text
	 * @param uid
	 */
	void updateText(String text, int uid);
	/**
	 * 更新地区
	 * @param province
	 * @param city
	 * @param uid
	 */
	void updateLocal(String province, String city, int uid);
	/**
	 * 更新性别
	 * @param sex
	 */
	void updateSex(char sex, int uid);
}
