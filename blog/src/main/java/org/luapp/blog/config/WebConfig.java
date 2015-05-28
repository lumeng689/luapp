package org.luapp.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.env.Environment;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.PathMatcher;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MatrixVariableMapMethodArgumentResolver;
import org.springframework.web.servlet.mvc.method.annotation.MatrixVariableMethodArgumentResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.util.UrlPathHelper;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by lumeng on 2015/4/4.
 */
@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "org.luapp.blog",includeFilters={@ComponentScan.Filter(type= FilterType.ANNOTATION,value = {org.springframework.stereotype.Controller.class})})
public class WebConfig extends WebMvcConfigurerAdapter {

    @Inject
    Environment env;

    @Override
    public void addFormatters(FormatterRegistry registry) {
// Add formatters and/or converters
    }

//    @Override
//    @Bean
//    public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
//        // Create or let "super" create the adapter
//        // Then customize one of its properties
//    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
// Configure the list of HttpMessageConverters to use
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LocaleInterceptor());
//        registry.addInterceptor(new
//                ThemeInterceptor()).addPathPatterns("/").excludePathPatterns("/admin/");
//        registry.addInterceptor(new SecurityInterceptor()).addPathPatterns("/secure/*");
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false).favorParameter(true);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/publicresources/").setCachePeriod(31556926);
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new MatrixVariableMethodArgumentResolver());
        argumentResolvers.add(new MatrixVariableMapMethodArgumentResolver());
    }

    @Bean
    MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }

    @Bean
    ViewResolver internalViewResolver() {
        // the view resolver bean ...
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

//
//    @Override
//    public void configurePathMatch(PathMatchConfigurer configurer) {
//        configurer
//                .setUseSuffixPatternMatch(true)
//                .setUseTrailingSlashMatch(false)
//                .setUseRegisteredSuffixPatternMatch(true)
//                .setPathMatcher(antPathMatcher())
//                .setUrlPathHelper(urlPathHelper());
//    }
//
//    @Bean
//    public UrlPathHelper urlPathHelper() {
//        //...
//        return null;
//    }
//
//    @Bean
//    public PathMatcher antPathMatcher() {
//        //...
//        return null;
//    }
}
