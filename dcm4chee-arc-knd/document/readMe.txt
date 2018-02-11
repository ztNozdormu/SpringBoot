SpringBoot注解意义:
   @SpringBootApplication 相当于@Configuration,@EnableAutoConfiguration,@ComponentScan
   @EnableConfigurationProperties({XXXProperties.class}):使用自定义的配置类在启动类上增加或者在配置类上增加@Component注解
   @Component:被该注解标注的bean将会被自动扫描,让自定义的类可以被spring容器管理,一般用于注解配置文件实体类开头
   @Configuration:自定义配置实体类用该注解标注后程序启动后将使用该配置类作为配置信息,用在配置组件类开头
   @MapperScan("mapperPath"):Mybatis mapper映射文件路径，扫描mapper映射文件
   @ServletComponentScan:用在启动类，扫描和注册自定义的Servlet,Filter,listener,interceptors;注意多模块项目需要指定具体模块的路径@ServletComponentScan("org.dcm4chee.arc.knd.common")
=====集成logBack
springBoot集成logBack:http://www.cnblogs.com/lixuwu/p/5804793.html

=====集成Mybatis
SpringBoot集成Mybatis:
         参考文章:http://blog.csdn.net/isea533/article/details/50359390
         mybatis3.4.1对应的mybatis-spring的版本为1.3.1 注意版本对应关系;
    两种方式:
    一:SpringBoot方式 POM引入:mybatis-spring-boot-starter
    二:传统的mybatis和Spring整合方式,参考博客:http://www.cnblogs.com/softidea/p/5699899.html：
            该方式注意各个版本的版本号(根据实际情况进行调整)。
         <!--mybatis与spring的整合-->
        <mybatis-spring.version>1.3.1</mybatis-spring.version>
        <!--myBatis版本-->
        <mybatis.version>3.4.1</mybatis.version>
        <!-- 分页插件 -->
        <pagehelper.version>5.1.2</pagehelper.version>
        <!-- 通用mapper -->
        <tk.mybatis.version>3.4.3</tk.mybatis.version>
        <!-- 通用mapper相关的依赖 -->
        <persistence-api.version>1.0</persistence-api.version>
        
    Mybatis主要配置类(注意相关注解的组合使用):
      MybatisProperties.java :Mybatis基本配置信息
      MybatisConfig.java :Mybatis配置信息设置对象
      MyBatisMapperScannerConfig.java :Mybatis mapper接口扫描配置类
      MyMapper.java :Mybatis通用接口
        
      注意点: 
      http://blog.csdn.net/jie873440996/article/details/70231260 通用mapper  BUG
      https://my.oschina.net/lengchuan/blog/876429  多数据源
   SpringBoot集成MybatisGenerator:
      Mybatis自动生成代码方式有三种:
        1.eclipse集成Generator
        2.pom.xml中定义插件，并在相关模块的pom.xml中引入相关的mybatis-generator-core jar包
        3.使用java工具类(MybatisGeneratorUtil.java)的方式生成。
      关键配置文件:generatorConfig.xml;
      配置文件中需要注意的几个配置:
      1.数据库配置:
        <jdbcConnection driverClass="com.mysql.cj.jdbc.Driver"  
                        connectionURL="jdbc:mysql://127.0.0.1:3306/jeesite?useUnicode=true&amp;characterEncoding=UTF8&amp;serverTimezone=UTC"  
                        userId="root"  
                        password="root">
      一般配置中属性之间是用分号(;)隔开;在该配置文件中需要将分号替换为 “&amp;”。
      2.自动生成代码文件路径(以自动生成实体类文件地址为例):    
        <javaModelGenerator targetPackage="org.dcm4chee.arc.knd.entity.model" targetProject="dcm4chee-arc-knd-entity\src\main\java">  
            <!-- 是否在当前路径下新加一层schema,eg：fase路径com.oop.eksp.user.model， true:com.oop.eksp.user.model.[schemaName] -->  
            <!--<property name="enableSubPackages" value="true" />-->  
            <!-- 是否针对string类型的字段在set的时候进行trim调用 -->  
            <!--<property name="trimStrings" value="true" />-->  
        </javaModelGenerator> 
      关键属性:targetPackage:项目中实体类package地址 一般是(【项目名称\src\main\java】【自定义的实体package路径】);这里取【自定义的实体package路径】,注意该处斜杠换成点
          targetProject:取【项目名称\src\main\java】 注意斜杠方向
      参考资料网址:http://www.jb51.net/article/96242.htm(号称完美配置文件详解)
      
      -----------------------------------------------以上为基础搭建----------------------------------------------------------------------
      -----------------------------------------------以下为基础通用功能的实现--------------------------------------------------------------
      SpringBoot自定义Servlet实现流程:
            参考网址:http://blog.csdn.net/lzdujing1/article/details/52344314
                代码方式实现:
	           1.自定义类并继承HttpServlet；重写doGet(HttpServletRequest rest,HttpServletResponse resp),重写
	           doPost(HttpServletRequest rest,HttpServletResponse resp).其他问题根据具体业务来实现            
	           2.注册自定义的Servlet。注册方式有多种，一种是在启动类里面定义方法返回一个，自定义的Servlet通过ServletRegistrationBean
        
      该类作用:@Component注解将该注册管理类统扫描到spring容器，使用ServletRegistrationBean包装和管理自定义的Servlet，
      通过@Bean注解使返回的ServletRegistrationBean能被spring容器管理，ServletRegistrationBean统一进行注册，可以注册多个，注意工厂方法上使用@Bean注解
                注解方式实现:
              1.在自定义的类上用 @WebServlet(urlPatterns="/xs/myservlet", description="Servlet的说明") // 不指定name的情况下，name默认值为类全路径
              2.在启动类上使用@ServletComponentScan
              3.自定义类继承HttpServlet,具体实现与代码方式一样，只是注册是通过注解自动注册
      SpringBoot自定义过滤器实现
               使用场景:过滤请web求
        1.创建一个类 2.implements Filter 3.根据业务重写方法:doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
                参考网址:http://blog.csdn.net/sun_t89/article/details/51916834
               http://blog.csdn.net/long290046464/article/details/76559880
                1、Filter功能
				filter功能，它使用户可以改变一个 request和修改一个response. Filter 不是一个servlet,它不能产生一个response,它能够在一个request到达servlet之前预处理request,也可以在离开 servlet时处理response.换种说法,filter其实是一个”servlet chaining”(servlet 链).
				一个Filter包括：
				1）、在servlet被调用之前截获;
				2）、在servlet被调用之前检查servlet request;
				3）、根据需要修改request头和request数据;
				4）、根据需要修改response头和response数据;
				5）、在servlet被调用之后截获.
      SpringBoot自定义监听器实现
                参考网址:http://blog.csdn.net/liaokailin/article/details/48186331
                @WebListener 
      SpringBoot自定义拦截器实现
             参考网址:http://blog.csdn.net/catoop/article/details/50501696
        补充参考网址:http://www.jianshu.com/p/c0c2fce3976d
      使用场景: HandlerInterceptor 的功能跟过滤器类似，但是提供更精细的的控制能力：在request被响应之前、request被响应之后、视图渲染之前以及request全部结束之后。我们不能通过拦截器修改request内容，但是可以通过抛出异常（或者返回false）来暂停request的执行。
       比如防xss攻击，在调用controller前对提交内容进行过滤等等
        
      SpringBoot全局异常处理的实现:
               参考博客:https://www.cnblogs.com/java-zhao/p/5769018.html
               参考博客:http://blog.csdn.net/tianyaleixiaowu/article/details/70145251
      SpringBoot全局统一返回格式的数据处理:
      
      --------------------------------------------------扩展模块的集成-------------------------------------------------
       SpringBoot集成Redis：
           参考网址:http://blog.csdn.net/GitChat/article/details/78751364
      --------------------------------------------------前端主要插件的集成和使用案例-----------------------------------------
      Webuploader上传图片插件：
           参考网址:http://blog.csdn.net/new_sara/article/details/51604997
     
      
      
      