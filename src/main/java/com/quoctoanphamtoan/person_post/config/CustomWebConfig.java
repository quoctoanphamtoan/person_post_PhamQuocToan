package com.quoctoanphamtoan.person_post.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.SortHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class CustomWebConfig implements WebMvcConfigurer {
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        SortHandlerMethodArgumentResolver sortResolver = new SortHandlerMethodArgumentResolver();
        sortResolver.setSortParameter("order-by");

        PageableHandlerMethodArgumentResolver pageResolver = new PageableHandlerMethodArgumentResolver(sortResolver);

        pageResolver.setPageParameterName("page-number");
        pageResolver.setSizeParameterName("page-size");
        pageResolver.setOneIndexedParameters(true);



        resolvers.add(pageResolver);
    }
}
