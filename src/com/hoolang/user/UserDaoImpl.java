package com.hoolang.user;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.hoolang.dao.base.BaseDaoImpl;
import com.hoolang.entity.User;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@Override
	public List<User> selectByName(String name) {
		// TODO Auto-generated method stub
		return find("from User u where u.username = '" + name + "'");
	}

	@Override
	public void updateNickname(String nickname, int uid) {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(date);
		String HQL = "UPDATE User SET nickname ='"+ nickname +"' , updateTime = '"+ dateString+"' WHERE uid = "+ uid ;
		getSessionFactory().getCurrentSession()
			.createQuery(HQL).executeUpdate();	
	}

	@Override
	public void updateText(String text, int uid) {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(date);
		String HQL = "UPDATE User SET text='"+ text +"' , updateTime = '"+ dateString+"' WHERE uid = "+ uid ;
		getSessionFactory().getCurrentSession()
			.createQuery(HQL).executeUpdate();	
	}

	@Override
	public void updateLocal(String province, String city, int uid) {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(date);
		String HQL = "UPDATE User SET province ='"+ province +"',  city ='"+ city +"' , updateTime = '"+ dateString+"' WHERE uid = "+ uid ;
		getSessionFactory().getCurrentSession()
			.createQuery(HQL).executeUpdate();	
		
	}

	@Override
	public void updateSex(char sex, int uid) {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(date);
		String HQL = "UPDATE User SET sex ='"+ sex +"', updateTime = '"+ dateString+"' WHERE uid = "+ uid ;
		getSessionFactory().getCurrentSession()
			.createQuery(HQL).executeUpdate();	
		
	}

	@Override
	public List<User> searchNickname(String nickname) {
		return find("from User u where u.username like '%" + nickname + "%'");
	}
}
