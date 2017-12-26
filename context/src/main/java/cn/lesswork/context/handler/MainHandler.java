package cn.lesswork.context.handler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.lesswork.context.enums.MethodType;

public final class MainHandler extends HttpServlet {
	
	private static final long serialVersionUID = -802065407121992348L;

	private void dispach(HttpServletRequest req, HttpServletResponse resp, MethodType method) {
		Dispacher.getInstance().dispach(req, resp, method);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		dispach(req, resp, MethodType.GET);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		dispach(req, resp, MethodType.POST);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		dispach(req, resp, MethodType.PUT);
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		dispach(req, resp, MethodType.DELETE);
	}
}
