package com.hoolang.dao.base;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("baseDao")
public class BaseDaoImpl<T> implements BaseDao<T> {
	// DAO组件进行持久化操作底层依赖的SessionFactory组件
	private SessionFactory sessionFactory;

	// 依赖注入SessionFactory所需的setter方法 
	//如果不加 @Autowired 会保存不了对象 出现java.lang.NullPointerException错误
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}
	
	
	// 根据ID加载实体
	@SuppressWarnings("unchecked")
	public T get(Class<T> entityClazz, Serializable id) {
		return (T) getSessionFactory().getCurrentSession().get(entityClazz, id);
	}
	
	// 根据ID懒加载实体
	@SuppressWarnings("unchecked")
	public T load(Class<T> entityClazz, Serializable id) {
		return (T) getSessionFactory().getCurrentSession().load(entityClazz, id);
	}

	// 保存实体
	public Serializable save(T entity) {
		return getSessionFactory().getCurrentSession().save(entity);
	}

	// 更新实体
	public void update(T entity) {
		getSessionFactory().getCurrentSession().saveOrUpdate(entity);
	}

	// 删除实体
	public void delete(T entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}

	// 根据ID删除实体
	public void delete(Class<T> entityClazz, Serializable id) {
		getSessionFactory()
				.getCurrentSession()
				.createQuery(
						"delete " + entityClazz.getSimpleName()
								+ " en where en.id = ?0").setParameter("0", id)
				.executeUpdate();
	}

	// 根据HQL条件删除实体
	@Override
	public void deleteBySQL(String SQL) {
		getSessionFactory().getCurrentSession()
		.createSQLQuery(SQL).executeUpdate();	
		
	}
	// 获取所有实体
	public List<T> findAll(Class<T> entityClazz) {
		return find("select en from " + entityClazz.getSimpleName() + " en");
	}

	// 获取实体总数

	public long findCount(Class<T> entityClazz) {
		List<?> l = find("select count(*) from " + entityClazz.getSimpleName());
		// 返回查询得到的实体总数
		if (l != null && l.size() == 1) {
			return (Long) l.get(0);
		}
		return 0;
	}
	
	// 获取实体总数

	public long findCountByID(Class<T> entityClazz, String id) {
		List<?> l = find("select count(*) from " + entityClazz.getSimpleName() + " " + id);
		// 返回查询得到的实体总数
		if (l != null && l.size() == 1) {
			return (Long) l.get(0);
		}
		return 0;
	}
	
	

	// 根据HQL语句查询实体
	@SuppressWarnings("unchecked")
	protected List<T> find(String hql) {
		return (List<T>) getSessionFactory().getCurrentSession()
				.createQuery(hql).list();
	}

	// 根据带占位符参数HQL语句查询实体
	@SuppressWarnings("unchecked")
	protected List<T> find(String hql, Object... params) {
		// 创建查询
		Query query = getSessionFactory().getCurrentSession().createQuery(hql);
		// 为包含占位符的HQL语句设置参数
		for (int i = 0, len = params.length; i < len; i++) {
			query.setParameter(i + "", params[i]);
		}
		return (List<T>) query.list();
	}


	/**
	 * 使用hql 语句进行分页查询操作
	 * 
	 * @param hql
	 *            需要查询的hql语句
	 * @param pageNo
	 *            查询第pageNo页的记录
	 * @param pageSize
	 *            每页需要显示的记录数
	 * @return 当前页的所有记录
	 */
	@SuppressWarnings("unchecked")
	protected List<T> findByPage(String hql, int pageNo, int pageSize) {
		// 创建查询
		return getSessionFactory().getCurrentSession().createQuery(hql)
				// 执行分页
				.setFirstResult((pageNo - 1) * pageSize)
				.setMaxResults(pageSize).list();
	}

	/**
	 * 使用hql 语句进行分页查询操作
	 * 
	 * @param hql
	 *            需要查询的hql语句
	 * @param params
	 *            如果hql带占位符参数，params用于传入占位符参数
	 * @param pageNo
	 *            查询第pageNo页的记录
	 * @param pageSize
	 *            每页需要显示的记录数
	 * @return 当前页的所有记录
	 */
	@SuppressWarnings("unchecked")
	protected List<T> findByPage(String hql, int pageNo, int pageSize,
			Object... params) {
		// 创建查询
		Query query = getSessionFactory().getCurrentSession().createQuery(hql);
		// 为包含占位符的HQL语句设置参数
		for (int i = 0, len = params.length; i < len; i++) {
			query.setParameter(i + "", params[i]);
		}
		// 执行分页，并返回查询结果
		return query.setFirstResult((pageNo - 1) * pageSize)
				.setMaxResults(pageSize).list();
	}

//	@Override
//	public List<T> findByHQL(String HQL) {
//		// TODO Auto-generated method stub
//		return find(HQL);
//	}
	
	/**
	 * 通过SQL查询,返回一个entityClazz实体
	 * 
	 */
//	@Override
//	public List<T> findBySQL(String SQL, Class<T> entityClazz) {
//		// TODO Auto-generated method stub
//		Query query = getSessionFactory().getCurrentSession().createSQLQuery(SQL);
//		//System.out.print(query);
//		query.setResultTransformer(Transformers.aliasToBean(entityClazz));
//		return (List<T>)query.list();
//	}

	@Override
	public List<T> findByHQL(String HQL, int max) {

		Query query = getSessionFactory().getCurrentSession().createQuery(HQL);
		// 为包含占位符的HQL语句设置参数
		query.setMaxResults(max);
		return (List<T>) query.list();
	}
	
	/**
	 * 通过SQL条件查询
	 * @param SQL
	 * @return
	 */
	public List<T> findBySQL(String SQL) {
		Query query = getSessionFactory().getCurrentSession().createSQLQuery(SQL);
		return (List<T>) query.list();
	}
	
	
}
