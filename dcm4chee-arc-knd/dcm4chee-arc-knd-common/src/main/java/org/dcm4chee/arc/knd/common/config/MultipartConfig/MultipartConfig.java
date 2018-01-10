package org.dcm4chee.arc.knd.common.config.MultipartConfig;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * SpringBoot上传配置详解
 * http://www.cnblogs.com/lmk-sym/p/6529232.html
 * 
 * @author Administrator
 * 其他参考:http://blog.csdn.net/w605283073/article/details/51340880
 *http://blog.csdn.net/linxingliang/article/details/52077816
 */
@Configuration
public class MultipartConfig {

    @Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("10MB");
        factory.setMaxRequestSize("10MB");
        return factory.createMultipartConfig();
    }

}