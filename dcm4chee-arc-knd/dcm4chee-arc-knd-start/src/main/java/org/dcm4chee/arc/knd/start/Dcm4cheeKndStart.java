package org.dcm4chee.arc.knd.start;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


/**
 * @SpringBootApplication 相当于@Configuration,@EnableAutoConfiguration,@ComponentScan
 * //使用自定义的配置类在启动类上增加@EnableConfigurationProperties({XXXProperties.class})或者在配置类上增加
 */
@MapperScan(basePackages = {"org.dcm4chee.arc.knd.dao.mapper"})
@ServletComponentScan("org.dcm4chee.arc.knd.common") //注意如果是多模块的项目，把具体需要扫描的模块路径加上
@SpringBootApplication(scanBasePackages = {"org.dcm4chee.arc.knd"}, exclude = {})
public class Dcm4cheeKndStart{

	  public static void main(String[] args) {
	      SpringApplication.run(Dcm4cheeKndStart.class, args);
	  }

}