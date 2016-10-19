package com.hoolang.version;


import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.hoolang.dao.base.BaseDaoImpl;
import com.hoolang.entity.User;

/**  
 * Package: com.hoolang.dao.impl  
 *  
 * File: VersionDaoImpl.java   
 *  
 * Author: hoolang   Date: 2015年7月17日  
 *  
 * Copyright @ 2015 Corpration 深圳后浪时代科技有限公司
 *   
 */
@Repository("versionDao")
public class VersionDaoImpl extends BaseDaoImpl<Version> implements VersionDao {

	@Override
	public List<Version> checkVersion() {
		// TODO Auto-generated method stub
		return find("from Version");
	}
}
