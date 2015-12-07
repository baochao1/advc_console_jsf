package com.cdel.util;

import org.apache.log4j.Priority;

public class DailyRollingFileAppender extends
		org.apache.log4j.DailyRollingFileAppender {
	@Override
	public boolean isAsSevereAsThreshold(Priority priority) {
		return this.getThreshold().equals(priority);
	}
}
