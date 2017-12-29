package cn.lesswork.context.enums;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public final class DataType {
	
	private DataType() {}
	
	public static final DataType DT = new DataType();
	
	public static final Class _INTEGER = Integer.class;
	
	public static final Class INTEGER = int.class;
	
	public static final Class _DOUBLE = Double.class;
	
	public static final Class DOUBLE = double.class;
	
	public static final Class _FLOAT = Float.class;
	
	public static final Class FLOAT = float.class;
	
	public static final Class _LONG = Long.class;
	
	public static final Class LONG = long.class;
	
	public static final Class _BOOLEAN = Boolean.class;
	
	public static final Class BOOLEAN = boolean.class;
	
	public static final Class REQUEST = HttpServletRequest.class;
	
	public static final Class RESPONSE = HttpServletResponse.class;
	
	public static final Class SESSION = HttpSession.class;

}
