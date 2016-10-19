package com.hoolang.dao.base;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;


/**
 */
public interface BaseDao<T>
{
	// 根据ID加载实体
	T get(Class<T> entityClazz , Serializable id);
	// 保存实体
	Serializable save(T entity);
	// 更新实体
	void update(T entity);
	// 删除实体
	void delete(T entity);
	// 根据ID删除实体
	void delete(Class<T> entityClazz , Serializable id);
	// 根据条件删除实体
	void deleteBySQL(String SQL);
	// 获取所有实体
	List<T> findAll(Class<T> entityClazz);
	// 获取实体总数
	long findCount(Class<T> entityClazz);
	// 根据ID统计实体数
	long findCountByID(Class<T> entityClazz, String id);
	// 根据HQL获取试题

	//List<T> findByHQL(String HQL);
		
	// 根据SQL获取试题
	List<T> findBySQL(String SQL);
	
	List<T> findByHQL(String HQL, int max);
	

}
