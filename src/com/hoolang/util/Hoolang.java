package com.hoolang.util;
/**  
 * Package: com.hoolang.util  
 *  
 * File: HoolangFinal.java   
 *  
 * Author: hoolang   Date: 2015年5月29日  
 *  
 * Copyright @ 2015 Corpration 深圳后浪时代科技有限公司
 *   
 */

public class Hoolang {

	/**
	 * 数据名称
	 */
	public static final String DATABASE_NAME = "nvshen";
	/**
	 * 点赞表名
	 */
	public static final String HL_LIKE = DATABASE_NAME + "." + "HL_LIKE";
	/**
	 * 用户表
	 */
	public static final String HL_USER = DATABASE_NAME + "." + "HL_USER";
	/**
	 * show表
	 */
	public static final String HL_POST = DATABASE_NAME + "." + "HL_POST";
	/**
	 * 评论表
	 */
	public static final String HL_COMMENT = DATABASE_NAME + "." + "HL_COMMENT";
	/**
	 * 用户头像服务器地址
	 */
	public static final String USER_ICON_URL = "http://115.126.73.22:8080/icons/";
	/**
	 * 用户图片服务器地址
	 */
	public static final String SHOW_PHOTO_URL = "http://115.126.73.22:8080/photos/";
	/**
	 * json返回的字段意义
	 */
	public static final String SUCCESS = "success";
	public static final String ERROR = "error";
	public static final String CANCEL = "cancel";
	public static final String DONE = "done";
	public static final String WRONG_REQUEST = "wrong request!!";
	
	/**
	 *  表示需要获取旧的信息
	 */
	public static final String OLDER = "older";
	/**
	 * 表示需要获取新的信息
	 */
	public static final String LATEST = "latest";
	
}
