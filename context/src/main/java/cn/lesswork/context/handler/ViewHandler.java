package cn.lesswork.context.handler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import com.alibaba.fastjson.JSON;

import cn.lesswork.context.anno.View;
import cn.lesswork.context.utils.FileUtil;

final class ViewHandler {
	private ViewHandler() {
	}
	private static final ViewHandler INSTANCE = new ViewHandler();
	protected static ViewHandler getInstance() {
		return INSTANCE;
	}
	public void outHtmlView() {
		
	}
	/**
	 * 位置.
	 */
	private static final String TEMPLOCAL = "src/main/resources/";
	/**
	 * 前缀.
	 */
	private static final String PREFIX = "view/";
	/**
	 * 后缀.
	 */
	private static final String STUFIX = ".html";
	/**
	 * 
	 * @param param
	 * @param value
	 * @return
	 */
	private Object outView(Object param, Object value) {
		
		if ((param instanceof Integer)
			|| (param instanceof String)
			|| (param instanceof Double)
			|| (param instanceof Float)
			|| (param instanceof Long)
			|| (param instanceof Boolean)) {
			return value;
		} else if (param instanceof Date) {
			return LocalDateTime.parse(value.toString()).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
		} else {
			return JSON.parse(JSON.toJSONString(value));
		}
	}
	
	public Object outView(Method work, Object value) {
		Object result = null;
		View view = work.getAnnotation(View.class);
		if (null != view) {
			String htmlPath = FileUtil.relativePath(TEMPLOCAL + PREFIX + view.value() + value.toString() + STUFIX);
			try {
				result = FileUtil.readFileByLine(htmlPath);
			} catch (IOException e) {
				if (e instanceof FileNotFoundException) {
					result = e.getMessage();
				}
			}
		} else {
			result = this.outView(value);
		}
		return result;
	}
	/**
	 * 
	 * @param param
	 * @param value
	 * @return
	 */
	private Object outView(Object value) {
		if ((value instanceof Integer)
			|| (value instanceof String)
			|| (value instanceof Double)
			|| (value instanceof Float)
			|| (value instanceof Long)
			|| (value instanceof Boolean)) {
			return value;
		} else if (value instanceof Date) {
			return LocalDateTime.parse(value.toString()).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
		} else {
			return JSON.toJSONString(value);
		}
	}
}
