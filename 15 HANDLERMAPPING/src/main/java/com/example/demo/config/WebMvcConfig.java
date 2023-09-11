package com.example.demo.config;

import com.example.demo.handler.CustomHandler;
import com.example.demo.interceptor.MemoInterceptor;
import com.example.demo.listener.CustomContextRefreshedListener;
import com.example.demo.listener.RequestHandledEventListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer{


	
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

	//HANDLER MAPPING
	//BeanNameUrlHandlerMapping : 요청 URL을 동일한 이름을 가진 빈에 매핑
	@Bean
	BeanNameUrlHandlerMapping beanNameUrlHandlerMapping() {
		return new BeanNameUrlHandlerMapping();
	}

	@Bean("/custom01")
	public CustomHandler handlerTest1() {
		return new CustomHandler();
	}

	//SimpleUrlHandlerMapping : 개발자가 직접 매핑정보를 설정, 정적자원에 대한 매핑정보 설정 기본값
	@Bean
	SimpleUrlHandlerMapping simpleUrlHandlerMapping() {

		SimpleUrlHandlerMapping handlerMapping = new SimpleUrlHandlerMapping();
		Map<String,Object> urlMap = new HashMap();
		urlMap.put("/custom02", new CustomHandler());
		handlerMapping.setUrlMap(urlMap);

		return handlerMapping;
	}

	@Bean
	RequestMappingHandlerMapping myRequestMappingHandlerMapping() throws NoSuchMethodException, SecurityException {

		RequestMappingHandlerMapping handlerMapping = new RequestMappingHandlerMapping();
		//Reflection으로 Method찾기
		Method method = CustomHandler.class.getMethod("WOW",null);
		System.out.println("methods : "+method.getName());


		//요청매핑정보 구성
		RequestMappingInfo mappingInfo = RequestMappingInfo
				.paths("/custom03")
				.methods(RequestMethod.GET)
				.build();

		//요청매핑, 핸들러, 함수 등록

		handlerMapping.registerMapping(mappingInfo, new CustomHandler(), method);

		return handlerMapping;
	}

}
