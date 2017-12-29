package cn.lesswork.context.boot;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;

import cn.lesswork.context.handler.MainHandler;

/**
 * 容器.
 *
 */
public final class LessWorkApp {
	
	private int port = 8080;
	public LessWorkApp port(final int port) {
		this.port = port;
		return this;
	}
    public void go(String[] args) {
        Server server = new Server(port);
        ServletContextHandler context = new ServletContextHandler(server, "/");
        context.addEventListener(new MyLister());
        server.setHandler(context);
        context.setSessionHandler(new SessionHandler());
        context.addServlet(MainHandler.class, "/");
        try {
			server.start();
			server.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    private static class MyLister implements ServletContextListener  {

		@Override
		public void contextInitialized(ServletContextEvent sce) {
			new LessBoot();
		}

		@Override
		public void contextDestroyed(ServletContextEvent sce) {
		}
    	
    }
}
