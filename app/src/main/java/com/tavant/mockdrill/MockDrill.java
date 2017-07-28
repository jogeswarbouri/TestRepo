package com.tavant.mockdrill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.MultipartFilter;

//@ComponentScan(basePackages = "com.*")
@SpringBootApplication(scanBasePackages="com.tavant.mockdrill")
//@EnableJpaRepositories("com.tavant.mockdrill.repositories")
//@EntityScan("com.tavant.mockdrill.model")
public class MockDrill extends SpringBootServletInitializer {  
	
	 private static Class applicationClass = MockDrill.class;
	 
	 @Override
		protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
			return application.sources(MockDrill.class);
		}

	 
	public static void main(String[] args) {
		SpringApplication.run(MockDrill.class, args);
    }  
	
	@Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();        
        resolver.setMaxUploadSizePerFile(52428800); 
        return resolver;
    }

    @Bean
    @Order(0)
    public MultipartFilter multipartFilter() {
            MultipartFilter multipartFilter = new MultipartFilter();
        multipartFilter.setMultipartResolverBeanName("multipartResolver");
        return multipartFilter;
    }

	    /*@Bean
	    public FilterRegistrationBean multipartFilterRegistrationBean() {
	        final MultipartFilter multipartFilter = new MultipartFilter();
	        final FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(multipartFilter);
	        filterRegistrationBean.addInitParameter("multipartResolverBeanName", "commonsMultipartResolver");
	        return filterRegistrationBean;
	    }
	    
	    public static final int UPLOAD_SIZE = 10000000;
	    
	    @Bean(name = "multipartResolver")
	    public CommonsMultipartResolver multipartResolver()
	    {
	      CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
	      multipartResolver.setMaxUploadSize( UPLOAD_SIZE );
	      return new CommonsMultipartResolver();
	    }*/
}   
