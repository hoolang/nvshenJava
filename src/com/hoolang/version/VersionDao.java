package com.hoolang.version;

import java.util.List;

import com.hoolang.dao.base.BaseDao;

/**  
 * Package: com.hoolang.dao.base  
 *  
 * File: VersionDao.java   
 *  
 * Author: hoolang   Date: 2015年7月17日  
 *  
 * Copyright @ 2015 Corpration 深圳后浪时代科技有限公司
 *   
 */
public interface VersionDao extends BaseDao<Version> {

	List<Version> checkVersion();
}
