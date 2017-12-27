<h1>一个轻量级的restful框架</h1><br/>

特征：<br/>
    1.内嵌jetty容器<br/>
    2.薄封装<br/>
    3.高效，高性能<br/>

环境：<br/>
	maven<br/>
	jdk1.8+<br/>

快速开始:
	<h2>pom.xml</h2>
	
	<dependencyManagement>
		<dependencies>
			<dependency>
				<groupId>com.lesswork.framework</groupId>
				<artifactId>lesswork</artifactId>
				<version>1.0.0</version>
				<scope>import</scope>
				<type>pom</type>
			</dependency>
		</dependencies>
	</dependencyManagement>
	<dependencies>
		<dependency>
			<groupId>com.lesswork.framework</groupId>
			<artifactId>context</artifactId>
		</dependency>
	</dependencies>
	
	<h2>less和启动</h2>
	
	import cn.lesswork.context.anno.Less;
	import cn.lesswork.context.anno.Work;

	@Less
	public class SampleLess {
		@Work("/hello")
		public String hello() {
			return "Hello less!";
		}
	}
