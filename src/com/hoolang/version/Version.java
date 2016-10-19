package com.hoolang.version;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

/**  
 * Package: com.hoolang.entity  
 *  
 * File: Version.java   
 *  
 * Author: hoolang   Date: 2015年7月17日  
 *  
 * Copyright @ 2015 Corpration 深圳后浪时代科技有限公司
 *   
 */
@Entity
@Table(name = "HL_VERSION")
public class Version implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String verseion;

	public Version(String verseion) {
		super();
		this.verseion = verseion;
	}

	public String getVerseion() {
		return verseion;
	}

	public void setVerseion(String verseion) {
		this.verseion = verseion;
	}
}
