package com.hoolang.version;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.hoolang.util.JsonTool;
import com.opensymphony.xwork2.ActionSupport;

/**  
 * Package: com.hoolang.action  
 *  
 * File: VersionAction.java   
 *  
 * Author: hoolang   Date: 2015年7月17日  
 *  
 * Copyright @ 2015 Corpration 深圳后浪时代科技有限公司
 *   
 */
@Controller
public class VersionAction extends ActionSupport {

	@Resource
	private VersionService versionService;
	
	public String checkVersion() throws Exception{
		
		HashMap map = versionService.checkVersion();
		
    	String params[] = {};
		JsonTool.fromObject(map, params);
		
		return null;
	}
}
