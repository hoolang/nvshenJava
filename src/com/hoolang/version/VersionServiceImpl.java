package com.hoolang.version;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**  
 * Package: com.hoolang.service.impl  
 *  
 * File: VersionServiceImpl.java   
 *  
 * Author: hoolang   Date: 2015年7月17日  
 *  
 * Copyright @ 2015 Corpration 深圳后浪时代科技有限公司
 *   
 */
@Service("versionService")
public class VersionServiceImpl implements VersionService {

	@Resource
	private VersionDao versionDao;
	
	@Override
	public HashMap checkVersion() {

		List<Version> list = versionDao.checkVersion();
		HashMap map = new HashMap();
		map.put("version", list);
		
		return map;
	}

}
