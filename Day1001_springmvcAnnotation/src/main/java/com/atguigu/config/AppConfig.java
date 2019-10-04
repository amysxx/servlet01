package com.atguigu.config;


import com.atguigu.controller.MyFirstInterceptor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.*;

//SpringMVCֻɨ��Controller��������
//useDefaultFilters=false ����Ĭ�ϵĹ��˹���
@ComponentScan(value="com.atguigu",includeFilters={
		@Filter(type=FilterType.ANNOTATION,classes={Controller.class})
},useDefaultFilters=false)
@EnableWebMvc
public class AppConfig  extends WebMvcConfigurerAdapter  {

	//����
	
	//��ͼ������
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		// TODO Auto-generated method stub
		//Ĭ�����е�ҳ�涼�� /WEB-INF/ xxx .jsp
		//registry.jsp();
		registry.jsp("/WEB-INF/views/", ".jsp");
	}
	
	//��̬��Դ����
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		// TODO Auto-generated method stub
		configurer.enable();
	}
	
	//������
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		//super.addInterceptors(registry);
		registry.addInterceptor(new MyFirstInterceptor()).addPathPatterns("/**");
	}

}
