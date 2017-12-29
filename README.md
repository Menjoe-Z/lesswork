<h1>一个轻量级的restful框架</h1><br/>

<h2>特征：</h2><br/>
&nbsp;&nbsp;&nbsp;&nbsp;1.内嵌jetty容器<br/>
&nbsp;&nbsp;&nbsp;&nbsp;2.薄封装<br/>
&nbsp;&nbsp;&nbsp;&nbsp;3.高效，高性能<br/>
&nbsp;&nbsp;&nbsp;&nbsp;4.完全 0 配置<br/>

<h2>环境：</h2><br/>
&nbsp;&nbsp;&nbsp;&nbsp;maven3.9+<br/>
&nbsp;&nbsp;&nbsp;&nbsp;jdk1.8+<br/>


<h2>快速开始:</h2>
	<h3>pom.xml</h3>
	
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

<h3>less和启动</h3>
	
	import cn.lesswork.context.anno.Less;
	import cn.lesswork.context.anno.Work;
	import cn.lesswork.context.boot.LessWorkApp;

	@Less
	public class SampleLess {

		@Work("/hello")
		public String hello() {
			return "Hello less!";
		}
		/**
		 * 启动器，无需添加额外配置,访问端口默认8080.
		 * @param args
		 */
		public static void main(String[] args) {
			new LessWorkApp().go(args);
		}
	}
