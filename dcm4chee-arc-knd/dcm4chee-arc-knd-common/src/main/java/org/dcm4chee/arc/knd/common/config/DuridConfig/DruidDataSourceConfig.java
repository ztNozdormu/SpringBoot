package org.dcm4chee.arc.knd.common.config.DuridConfig;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.dcm4chee.arc.knd.common.config.properties.DataSourceConfigProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import com.alibaba.druid.pool.DruidDataSource;

/**
 * 数据库连接池Druid的配置类
 * DruidDBConfig类被@Configuration标注，用作配置信息； 
 * DataSource对象被@Bean声明，为Spring容器所管理， 
 * @Primary表示这里定义的DataSource将覆盖其他来源的DataSource。
 *jdbc.url=${jdbc.url} 
 *最新的支持方式如下: 
 *jdbc.url=@jdbc.url@  
 */
@Configuration
public class DruidDataSourceConfig {
	
  private Logger logger = LoggerFactory.getLogger(DruidDataSourceConfig.class);
     @Autowired 
     public DataSourceConfigProperties dbConfig;

    @Bean // 声明其为Bean实例
    @Primary // 在同样的DataSource中，首先使用被标注的DataSource
    public DataSource dataSource() {
        DruidDataSource datasource = new DruidDataSource();
  
        datasource.setUrl(dbConfig.getDbUrl());
        datasource.setUsername(dbConfig.getUsername());
        datasource.setPassword(dbConfig.getPassword());
        datasource.setDriverClassName(dbConfig.getDriverClassName());

        // configuration
        datasource.setInitialSize(dbConfig.getInitialSize());
        datasource.setMinIdle(dbConfig.getMinIdle());
        datasource.setMaxActive(dbConfig.getMaxActive());
        datasource.setMaxWait(dbConfig.getMaxWait());
        datasource.setTimeBetweenEvictionRunsMillis(dbConfig.getTimeBetweenEvictionRunsMillis());
        datasource.setMinEvictableIdleTimeMillis(dbConfig.getMinEvictableIdleTimeMillis());
        datasource.setValidationQuery(dbConfig.getValidationQuery());
        datasource.setTestWhileIdle(dbConfig.isTestWhileIdle());
        datasource.setTestOnBorrow(dbConfig.isTestOnBorrow());
        datasource.setTestOnReturn(dbConfig.isTestOnReturn());
        datasource.setPoolPreparedStatements(dbConfig.isPoolPreparedStatements());
        datasource.setMaxPoolPreparedStatementPerConnectionSize(dbConfig.getMaxPoolPreparedStatementPerConnectionSize());
        try {
            datasource.setFilters(dbConfig.getFilters());
        } catch (SQLException e) {

        }
        datasource.setConnectionProperties(dbConfig.getConnectionProperties());
        System.out.println("数据源初始化:"+"88888888888888****数据库密码"+dbConfig.getPassword()+"数据源类型为:"+dbConfig.getDriverClassName());
        return datasource;
    }
    
    
}
