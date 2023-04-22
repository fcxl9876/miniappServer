package xin.fcxl9876.miniappserver.conf;

import com.google.common.base.Predicates;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import xin.fcxl9876.miniappserver.annotation.AuthRest;
import xin.fcxl9876.miniappserver.annotation.OpenRest;

import java.util.ArrayList;
import java.util.List;

/**
 * Class description
 *
 * @author ljf
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    private String basePackage = "xin.fcxl9876.miniappserver";

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("open接口").select()
                .apis(Predicates.and(RequestHandlerSelectors.basePackage(basePackage),
                        RequestHandlerSelectors.withClassAnnotation(OpenRest.class),
                        Predicates.not(RequestHandlerSelectors.withMethodAnnotation(AuthRest.class))))
                .build().apiInfo(apiInfo());
    }

    @Bean
    public Docket createProtectRestApi() {
        List<Parameter> pars = new ArrayList<>();
        ParameterBuilder tokenPar = new ParameterBuilder();
        tokenPar.name("token").description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(true).order(0);
        pars.add(tokenPar.build());
        return new Docket(DocumentationType.SWAGGER_2).groupName("safe接口").select()
                .apis(Predicates.and(RequestHandlerSelectors.basePackage(basePackage),
                        Predicates.or(RequestHandlerSelectors.withClassAnnotation(AuthRest.class),
                                RequestHandlerSelectors.withMethodAnnotation(AuthRest.class))))
                .build().globalOperationParameters(pars)
                .apiInfo(safeApiInfo());
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("default")
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("数据提供服务   OPEN RESTful API文档").description("开放接口参考和查看API详细信息")
                .termsOfServiceUrl("http://www.fcxl9876.xin/")
                .contact(new Contact("kskQAQ", "", ""))
                .version("1.0").build();
    }


    private ApiInfo safeApiInfo() {
        return new ApiInfoBuilder().title("监控平台   SAFE RESTful API文档").description("安全接口参考和查看API详细信息")
                .termsOfServiceUrl("http://www.fcxl9876.xin/")
                .contact(new Contact("kskQAQ", "", ""))
                .version("1.0").build();
    }


}
