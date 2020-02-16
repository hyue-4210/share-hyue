package com.powerlong.sharera.configer;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: sharera
 * @description:
 * @author: liu.j
 * @create: 2019-11-13 09:30
 **/
@Configuration
public class CorsConfig extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        // 防止流读取一次后就没有了, 所以需要将流继续写出去，提供后续使用
        ServletRequest requestWrapper = new BodyReaderHttpServletRequestWrapper(httpServletRequest);
        String json = HttpHelper.getBodyString(requestWrapper);
        filterChain.doFilter(requestWrapper, httpServletResponse);
    }


}

