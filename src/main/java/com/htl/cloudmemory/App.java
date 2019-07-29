package com.htl.cloudmemory;

import java.nio.charset.StandardCharsets;
import javax.servlet.MultipartConfigElement;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import com.google.common.base.Predicate;
import com.htl.cloudmemory.core.apigroup.APIGroup_api_1_0;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author hetianlong
 * @since 2018.12.03
 *
 */
@EnableSwagger2
@EnableAutoConfiguration
@SpringBootApplication
@MapperScan("com.htl.cloudmemory.dao")
public class App {
	
	private static final String SWAGGER_SCAN_BASE_PACKAGE = "com.htl.cloudmemory.controller";
	
    /**
     * 使用RestTemplateBuilder来实例化RestTemplate对象，
     * spring默认已经注入了RestTemplateBuilder实例
     *
     * @return RestTemplate
     */
    @Bean
    public RestTemplate restTemplate() {
    	//复杂构造函数的使用  
    	SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();  
    	requestFactory.setConnectTimeout(15000);// 设置超时  
    	requestFactory.setReadTimeout(15000);
    	RestTemplate template = new RestTemplate(requestFactory);
    	template.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
    	//利用复杂构造器可以实现超时设置，内部实际实现为 HttpClient  
    	return template;
    }
    
    /**
     * 文件上传配置
     *
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 单个文件最大
        factory.setMaxFileSize("204800KB"); // KB,MB
        return factory.createMultipartConfig();
    }
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
		System.out.println("start success.");
	}
	
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(SWAGGER_SCAN_BASE_PACKAGE))
                .paths(PathSelectors.any())
                .build();
    }
    
	 /**
	  * 
	  * @Description: 用于swagger,放疗1.0.15版本需求
	  * @author he.tianlong
	  * @date 2018年8月15日
	  * @return
	  */
	 @Bean
    public Docket api_1_0() {
        Predicate<RequestHandler> predicate = new Predicate<RequestHandler>() {
            @Override
            public boolean apply(RequestHandler input) {

                if (input.isAnnotatedWith(ApiOperation.class) && input.isAnnotatedWith(APIGroup_api_1_0.class)){
                    return true;
                }
                return false;
            }
        };

        return new Docket(DocumentationType.SWAGGER_2).groupName("云存1.0功能").apiInfo(
                new ApiInfoBuilder().title("云存1.0相关接口").description("云存1.0相关接口").version("1.0").build())
                .select().apis(predicate).paths(PathSelectors.any()).build();

    }
    
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("云存相关接口")
                .description("云存相关接口")
                .version("1.0")
                .build();
    }
}
