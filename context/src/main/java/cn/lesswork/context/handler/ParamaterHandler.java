package cn.lesswork.context.handler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.lesswork.context.anno.Param;

final class ParamaterHandler {
	
	private ParamaterHandler() {
	}
	private static final ParamaterHandler instance = new ParamaterHandler();
	protected static ParamaterHandler getInstance() {
		return instance;
	}
	
	/**
	 * 获取所有的参数.
	 * @param req 清求
	 * @param object 对象
	 * @param work 函数
	 * @return 参数数组.
	 */
	public Object getAllParameter(HttpServletRequest req, Object object, Method work) {
		int paramSize =  work.getParameterCount();
		Object[] objArr = null;
		if (paramSize > 0) {
			Map<String, String[]> params = req.getParameterMap();
			objArr = new Object[paramSize];
			Parameter[] parameters = work.getParameters();
			for (int i = 0; i < parameters.length; i++) {
				Parameter param = parameters[i];
				Param paramAnn = param.getAnnotation(Param.class);
				objArr[i] = this.paramSizeJude(param, params.get(paramAnn.value()));
			}
		}
		Object result = null;
		try {
			result = work.invoke(object, objArr);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 参数个数.
	 * @param param 形参
	 * @param values url传值的参数
	 * @return 参数数组
	 */
	private Object paramSizeJude(Object param, String[] values) {
		Object obj = null;
		if (values.length == 1) {
			obj = this.typeJude(param, values[0]);
		} else {
			Object[] childArgs = new Object[values.length];
			int i = 0;
			for (String value : values) {
				childArgs[i] = this.typeJude(param, value);
				i++;
			}
			obj = childArgs;
		}
		return obj;
	}
	/**
	 * 
	 * @param param
	 * @param value
	 * @return
	 */
	private Object typeJude(Object param, String value) {
		if (param instanceof Integer) {
			return Integer.parseInt(value);
		} else if (param instanceof String) {
			return (String) value;
		} else if (param instanceof Double) {
			return new Double(value);
		} else if (param instanceof Float) {
			return new Float(value);
		} else if (param instanceof Long) {
			return new Long(value);
		} else if (param instanceof Boolean) {
			return new Boolean(value);
		} else if ((param instanceof Date) || (param instanceof LocalDateTime)) {
			return LocalDateTime.parse(value);
		} else {
			return value;
		}
	}
}
