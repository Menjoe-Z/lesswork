package cn.lesswork.context.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface View {
	/**
	 * 视图前缀;
	 * 		需要配合String类型返回的work，
	 * 		返回一般为html页面名称, 
	 * 		即是页面在项目中地址，
	 * 		解析为"前缀+html页面名称"
	 * 		默认是根路径，在此情况下需要在返回中返回全路径
	 * 例: 
	 * 		@View(/page/),  
	 * 		return "index";
	 * 结果:
	 * 		/page/index.html
	 */
	String value() default "/";
}
