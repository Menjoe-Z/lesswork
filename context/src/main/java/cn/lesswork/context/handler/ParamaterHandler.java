package cn.lesswork.context.handler;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.lesswork.context.anno.Param;
import cn.lesswork.context.enums.DataType;

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
	 * @param resp 响应
	 * @param object 对象
	 * @param work 函数
	 * @return 参数数组.
	 * @throws Exception 所有异常都抛出
	 */
	public Object getAllParameter(HttpServletRequest req, HttpServletResponse resp, Object object, Method work) throws Exception {
		int paramSize =  work.getParameterCount();
		Object[] objArr = null;
		if (paramSize > 0) {
			Map<String, String[]> params = req.getParameterMap();
			objArr = new Object[paramSize];
			Parameter[] parameters = work.getParameters();
			for (int i = 0; i < parameters.length; i++) {
				Parameter param = parameters[i];
				if (param.getType().equals(DataType.REQUEST)) {
					objArr[i] = req;
				} else if (param.getType().equals(DataType.RESPONSE)) {
					objArr[i] = resp;
				} else if (param.getType().equals(DataType.SESSION)) {
					objArr[i] = req.getSession();
				} else {
					Param paramAnn = param.getAnnotation(Param.class);
					Object resultItem = this.paramSizeJudg(param.getType(), params.get(paramAnn.value()));
					if (null == resultItem) { //参数不匹配时, 参数不是必须时
						boolean must = paramAnn.must();
						if (must) {
							return String.format("【%s】 is requed", paramAnn.value());
						}
						resultItem = this.typeJudg(param.getType(), paramAnn.fixed());
					}
					objArr[i] = resultItem;
				}
			}
		}
		return work.invoke(object, objArr);
	}
	private Object servletObjJudg() {
		return null;
	}
	/**
	 * 单个参数和多个参数处理.
	 * @param param 形参
	 * @param values url传值的参数
	 * @return 参数数组
	 */
	private Object paramSizeJudg(Class param, String[] values) {
		if (null == values) {
			return null;
		}
		Object obj = null;
		if (values.length == 1) {
			obj = this.typeJudg(param, values[0]);
		} else {
			Object[] childArgs = new Object[values.length];
			int i = 0;
			for (String value : values) {
				childArgs[i] = this.typeJudg(param, value);
				i++;
			}
			obj = childArgs;
		}
		return obj;
	}
	/**
	 * url携带参数的类型转换.
	 * @param param 方法上的参数类型.
	 * @param value 地址栏中携带的参数值
	 * @return 转换后的参数.
	 */
	private Object typeJudg(Class param, String value) {
		DataType DT = DataType.DT;
		if (param.equals(DT._INTEGER) || param.equals(DT.INTEGER)) {
			return Integer.parseInt(value);
		} else if (param.equals(DT._DOUBLE) || param.equals(DT.DOUBLE)) {
			return new Double(value);
		} else if (param.equals(DT._FLOAT) || param.equals(DT.FLOAT)) {
			return new Float(value);
		} else if (param.equals(DT._LONG) || param.equals(DT.LONG)) {
			return new Long(value);
		} else if (param.equals(DT._BOOLEAN) || param.equals(DT.BOOLEAN)) {
			return new Boolean(value);
		} else {
			return value;
		}
	}
}
