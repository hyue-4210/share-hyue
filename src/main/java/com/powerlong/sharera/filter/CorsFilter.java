package com.powerlong.sharera.filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsFilter implements WebMvcConfigurer {
	@Override
    public void addCorsMappings(CorsRegistry registry) {
        //设置允许跨域的路径  
        registry.addMapping("/**")  
                //设置允许跨域请求的域名  
                .allowedOrigins("*")  
                //是否允许证书 不再默认开启  
                .allowCredentials(true)  
                //设置允许的方法  
                .allowedMethods("*")  
                //跨域允许时间  
                .maxAge(3600);  
    }
	 /**
     * 添加静态资源文件，外部可以直接访问地址
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        //需要告知系统，这是要被当成静态文件的！
        //第一个方法设置访问路径前缀，第二个方法设置资源路径
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
