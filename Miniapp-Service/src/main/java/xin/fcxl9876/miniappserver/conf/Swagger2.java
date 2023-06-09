package xin.fcxl9876.miniappserver.conf;

import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

//@Configuration
//@EnableSwagger2
public class Swagger2 { 
	@Bean
    public Docket createRestApi(){
		 return new Docket(DocumentationType.SWAGGER_2)
	                .apiInfo(apiInfo())
	                .select()
	                .apis(RequestHandlerSelectors.basePackage("xin.fcxl9876.miniappserver"))
	                .paths(PathSelectors.any())
	                .build();
	}
	
	private ApiInfo apiInfo(){
	    return new ApiInfoBuilder()
	            .title("数据提供服务")
	            .description("API描述")
	            .version("1.0")
	            .build();
	}


}