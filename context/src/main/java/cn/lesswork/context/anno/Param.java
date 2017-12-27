package cn.lesswork.context.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Param {

	public String value();
	
	/**
	 * 是否必须.
	 * @return 
	 */
	boolean must() default true;
	/**
	 * 默认值.
	 * @return
	 */
	String fixed() default "";
}
