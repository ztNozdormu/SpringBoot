SpringBoot注解意义:
   @SpringBootApplication 相当于@Configuration,@EnableAutoConfiguration,@ComponentScan
   @EnableConfigurationProperties({XXXProperties.class}):使用自定义的配置类在启动类上增加或者在配置类上增加@Component注解
   @Component:被该注解标注的bean将会被自动扫描,注解配置文件实体类开头
   @Configuration:自定义配置实体类用该注解标注后程序启动后将使用该配置类作为配置信息,用在配置组件类开头
   @MapperScan("mapperPath"):Mybatis mapper映射文件路径，扫描mapper映射文件
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
      SpringBoot自定义Servlet实现:
      SpringBoot自定义过滤器实现
      SpringBoot自定义监听器实现
      SpringBoot自定义拦截器实现
      
      SpringBoot全局异常处理的实现:
      SpringBoot全局统一返回格式的数据处理:
      
      
      
      