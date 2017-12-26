package cn.lesswork.context.model;

import java.lang.reflect.Method;

public final class RouterBean {
	
	private Class clazz;
	
	private Method method;

	public Class getClazz() {
		return clazz;
	}

	public void setClazz(Class clazz) {
		this.clazz = clazz;
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	public RouterBean(Class clazz, Method method) {
		super();
		this.clazz = clazz;
		this.method = method;
	}
	
	public RouterBean() {
	}

}
