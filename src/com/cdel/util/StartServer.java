package com.cdel.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class StartServer implements ServletContextListener {
	public void contextInitialized(ServletContextEvent ctxEvt) {
		System.setProperty("log4jHome", Contants.logPath);
	}

	public void contextDestroyed(ServletContextEvent ctxEvt) {
		System.gc();
	}

}
