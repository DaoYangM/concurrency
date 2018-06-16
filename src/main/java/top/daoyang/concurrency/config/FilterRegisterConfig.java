package top.daoyang.concurrency.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.daoyang.concurrency.filter.HttpFilter;

import javax.servlet.Filter;

@Configuration
public class FilterRegisterConfig {

    @Autowired
    private HttpFilter httpFilter;

    @Bean
    public FilterRegistrationBean<Filter> registrationBean() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(httpFilter);
        registrationBean.addUrlPatterns("/threadLocal/*");

        return registrationBean;
    }
}
