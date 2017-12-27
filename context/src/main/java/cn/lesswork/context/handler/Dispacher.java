package cn.lesswork.context.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.lesswork.context.anno.Param;
import cn.lesswork.context.enums.MethodType;
import cn.lesswork.context.model.Container;
import cn.lesswork.context.model.RouterBean;

final class Dispacher {
	private Dispacher() {
	}
	private static final Dispacher instance = new Dispacher();
	protected static Dispacher getInstance() {
		return instance;
	}
	/**
	 * 容器.
	 */
	private static Container container = Container.INSTANCE;
	/**
	 * 储存类实例.
	 */
	private static final Map<Class, Object> CLASS_INSTANCES = new HashMap<>();
	/**
	 * 调度.
	 * @param req 请求
	 * @param resp 响应
	 * @param method 请求类型
	 * @throws IOException 
	 */
	protected void dispach(HttpServletRequest req, HttpServletResponse resp, MethodType method) {
		PrintWriter out = null;
		try {
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("text/html;charset=UTF-8");
			out = resp.getWriter();
			String uri = req.getRequestURI();
			Map<String, RouterBean> roter = container.getRouter();
			RouterBean routerBean = roter.get(uri);
			if (null == routerBean) {
				out.print(String.format("you have no 【%s】 work!", uri));
				return;
			}
			Method work = routerBean.getMethod();
			Class<?> clazz = routerBean.getClazz();
			Object object = this.CLASS_INSTANCES.get(clazz);
			if (null == object) {
				object = clazz.newInstance();
				this.CLASS_INSTANCES.put(clazz, object);
			}
			Object resultObj = ParamaterHandler.getInstance().getAllParameter(req, object, work);
			out.print(ViewHandler.getInstance().outView(work, resultObj));
		} catch (IllegalArgumentException e) {
			out.print("不支持的参数数据类型:\t" + e);
		} catch (Exception e) {
			out.print(e);
		} finally {
			out.flush();
			out.flush();
		}
	}
}
