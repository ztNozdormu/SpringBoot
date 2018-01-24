package org.dcm4chee.arc.knd.common.filter.userDefinedFilter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Filter 统一注册类
 * @author Administrator
 *
 */
@Component
public class RegistrationFilterBeanManager {
	@Bean
	public FilterRegistrationBean registrationFilterBean1() {
			FilterRegistrationBean frb = new FilterRegistrationBean(new HTTPBasicAuthorizeAttribute());
			frb.addUrlPatterns("/filter/filterTest1");
		return frb;
	}
	@Bean
	public FilterRegistrationBean registrationFilterBean2() {
		FilterRegistrationBean frb = new FilterRegistrationBean(new MyFilter());
		frb.addUrlPatterns("/filter/filterTest2");
		return frb;
	}
}
