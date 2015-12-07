package com.cdel.database;

import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Service;
@Service("dsAspectBean")
public class DSAspect {
	public void doBefore(JoinPoint jp) {
		DbContextHolder.setDbType("jiaowu");
	}

}
