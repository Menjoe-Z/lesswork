package cn.lesswork.context.model;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Container {
	/**
	 * 实例.
	 */
	INSTANCE;
	/**
	 * 所有类.
	 */
	private List<Class> allClass = new ArrayList<>();
	/**
	 * less类.
	 */
	private List<Class> less = new ArrayList<>();
	/**
	 * 所有方法.
	 */
	private List<Method> methods = new ArrayList<>();
	/**
	 * 路由映射.
	 */
	private Map<String, RouterBean> router = new HashMap<>();
	
	public List<Class> getAllClass() {
		return allClass;
	}

	public void setAllClass(Class Clazz) {
		this.allClass.add(Clazz);
	}

	public List<Class> getLess() {
		return less;
	}

	public void setLess(Class Clazz) {
		this.less.add(Clazz);
	}

	public List<Method> getMethods() {
		return methods;
	}

	public void addMethods(Method method) {
		this.methods.add(method);
	}

	public Map<String, RouterBean> getRouter() {
		return router;
	}

	public void setRouter(String key, RouterBean value) {
		router.put(key, value);
	}
}
