package org.dcm4chee.arc.knd.common.config.MybatisConfig;

import java.util.Properties;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;
/**
 * MyBatis扫描接口，使用的tk.mybatis.spring.mapper.MapperScannerConfigurer <br/>
 * 如果你不使用通用Mapper，可以改为org.xxx...
 *
 */
@Configuration
//TODO 注意，由于MapperScannerConfigurer执行的比较早，所以必须有下面的注解
@AutoConfigureAfter(MybatisConfig.class)
public class MyBatisMapperScannerConfig {

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        //不使用通用mapper的时候使用，org包开头的MapperScannerConfigurer扫描配置类  
        //MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();  
        //使用通用mapper的时候，使用tk开头的MapperScannerConfigurer扫描配置类 
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        //注意这里的sqlSessionFactory就是MybatisConfig里面的sqlSessionFactoryBean方法，注解bean的名字  
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        //扫描mapper接口 通过这些接口调用sql的配置，操作数据库 
        mapperScannerConfigurer.setBasePackage("org.dcm4chee.arc.knd.dao.mapper");
        Properties properties = new Properties();
        // 这里要特别注意，不要把MyMapper放到 basePackage 中，也就是不能同其他Mapper一样被扫描到。 
        properties.setProperty("mappers","org.dcm4chee.arc.knd.common.MapperCommonUtil.MyMapper" );//MyMapper.class.getName()
        properties.setProperty("notEmpty", "false");
        properties.setProperty("IDENTITY", "MYSQL");
        mapperScannerConfigurer.setProperties(properties);
        return mapperScannerConfigurer;
    }

}
