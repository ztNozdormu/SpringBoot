package org.dcm4chee.arc.knd.web.controller;

import javax.annotation.Resource;

import org.dcm4chee.arc.knd.common.config.properties.DataSourceConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dcm4chee-arc")
public class LoginController {
//	@Autowired
//
//	@Resource(name = "dataSourceConfigProperties")
	@Autowired
//	@Resource(name = "dataSourceConfigProperties")  
	public DataSourceConfigProperties dbConfigProperties;
	
    @RequestMapping("/login")
    public String login() {
    	System.out.println("数据源配置对象注入测试:"+dbConfigProperties.getDriverClassName());
        return "login"+"数据源配置对象注入测试:"+dbConfigProperties.getDriverClassName();
    }
    
    @RequestMapping("/index")
    public String index() {
        return "index";
    }
    
    @RequestMapping("/uploadFile")
    public String uploadFile() {
        return "uploadFile";
    }
    
    @RequestMapping("/duridInfo")
    public String duridInfo() {
    	System.out.println("数据源配置对象注入测试:"+dbConfigProperties.getDriverClassName());
        return "当前使用的数据源是:"+dbConfigProperties.getDriverClassName();
    }
    
}
