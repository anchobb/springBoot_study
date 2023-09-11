package com.example.demo.config;

import com.example.demo.interceptor.MemoInterceptor;
import com.example.demo.listener.CustomContextRefreshedListener;
import com.example.demo.listener.RequestHandledEventListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(20971520);			//20MB (전체 업로드 허용 사이즈)
		multipartResolver.setMaxUploadSizePerFile(20971520);	//20MB (파일 한개당 허용가능한 업로드 사이즈)
		multipartResolver.setMaxInMemorySize(20971520);			//20MB (캐시 공간)
		return multipartResolver;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	//INTERCEPTOR
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new MemoInterceptor())
				.addPathPatterns("/memo/**") //메모의 하위경로 모두 포함
				.excludePathPatterns("/resources/**"); //resources 하위경로 모두 제외
	}

	//Listener Added
	@Bean
	public CustomContextRefreshedListener customContextRefreshedListener() {
		return new CustomContextRefreshedListener();
	}

	//RequestHandledEventListener add
	@Bean
	public RequestHandledEventListener requestHandledEventListener() {
		return new RequestHandledEventListener();
	}

}
