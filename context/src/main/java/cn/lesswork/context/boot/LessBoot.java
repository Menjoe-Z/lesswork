package cn.lesswork.context.boot;


import java.io.File;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;

import cn.lesswork.context.anno.Less;
import cn.lesswork.context.anno.Work;
import cn.lesswork.context.model.Container;
import cn.lesswork.context.model.RouterBean;
final class LessBoot {
	protected LessBoot() {
		this.handleWork();
	}
	
	private static final Logger LOG = Log.getLogger(LessBoot.class);
	
	private static Container container = Container.INSTANCE;
	
	/**
	 * 从class文件中里面加载所有类
	 * @param filePath 文件路径
	 */
	public void bootClassLoader(String filePath) {
		File root = new File(filePath);
		File[] files = root.listFiles();
		for (File f :files) {
			if (null == f) {
				continue;
			}
			if (f.isDirectory()) {
				bootClassLoader(f.getAbsolutePath());
			} else {
				String fileName = f.getName();
				String stufix = fileName.substring((fileName.indexOf(".") + 1), fileName.length());
				if ("class".equals(stufix)) {
					try {
						String absolutePath = f.getAbsolutePath();
						String className = absolutePath.substring((absolutePath.indexOf("classes")), absolutePath.length());
						className = className.replaceAll("classes\\\\", "");
						className = className.replaceAll("\\\\", ".");
						className = className.replaceAll(".class", "");
						container.setAllClass(Class.forName(className));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	/**
	 * 类过滤,过滤出所有less类.
	 */
	private void handleLess() {
		String rootPath = System.getProperty("user.dir");
		this.bootClassLoader(rootPath);
		for (Class cls : container.getAllClass()) {
			Less[] less = (Less[]) cls.getAnnotationsByType(Less.class);
			if (less.length > 0) {
				container.setLess(cls);
			}
		}
	}
	/**
	 * less类处理,过滤出所有less类中的work方法.
	 */
	private void handleWork() {
		this.handleLess();
		List<Class> list = container.getLess();
		System.out.println("Starting......");
		for (Class cls :list) {
			Method[] method = cls.getMethods();
			Less less = (Less) cls.getAnnotation(Less.class);
			String lessPre = less.value();
			for (Method md : method) {
				Work[] work = (Work[]) md.getAnnotationsByType(Work.class);
				if (work.length > 0) {
					String key = lessPre + work[0].value();
					Map<String, RouterBean> bean = container.getRouter();
					if (bean.containsKey(key)) {
						System.out.println("******************************************\n");
						System.out.println(String.format("Application failed to start, the work has exist：【%s】", key));
						System.out.println("\n******************************************");
						System.exit(0);
					}
					container.setRouter(key, new RouterBean(cls, md));
					LOG.info(String.format("add a work【%s】", key));
				}
			}
		}
	}
	
}
