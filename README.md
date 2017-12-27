<h3>lesswork 一个轻量级的restful框架</h3><br/>

特征：
    1.内嵌jetty容器
    2.薄封装
    3.高效，高性能

环境：
	maven
	jdk1.8+

快速开始:
	pom.xml
	
	import cn.lesswork.context.anno.Less;
	import cn.lesswork.context.anno.Work;

	@Less
	public class SampleLess {
		@Work("/hello")
		public String hello() {
			return "Hello less!";
		}
	}
