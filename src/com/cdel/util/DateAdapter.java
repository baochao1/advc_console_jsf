package com.cdel.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

/**
 * 时间转化适配器 
 * @author xxg
 *
 */
public class DateAdapter implements JsonDeserializer<Date> {
	private final DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public Date deserialize(JsonElement arg0, java.lang.reflect.Type arg1,
			JsonDeserializationContext arg2) throws JsonParseException {
		try {
			return df.parse(arg0.getAsString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
