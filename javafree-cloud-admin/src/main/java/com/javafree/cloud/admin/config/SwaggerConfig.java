package com.javafree.cloud.admin.config;
/*
 * @Description: 修改swagger默认配置
 * @Author gwz  gwz126@126.com
 * @Date 2021/11/20 18:58
 * @version V1.0
 */

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    /**
     * 将Docket注入容器中，配置扫描路径和分组名等信息
     * @return
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                //.groupName("机构管理相关API") //加了groupname 无法访问  v3/api-docs 无法被knife4j 显示
                .select()
                //.apis(RequestHandlerSelectors.basePackage("com.javafree.cloud"))
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                //自己定义默认返回状态消息 去掉Springfox始终会生成的响应消息（401,403…）
                .useDefaultResponseMessages(false);
    }

    /**
     * 配置swagger相关标题、版本等参数
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                  .title("组织机构接口文档")
                  .description("组织机构管理相关API，包括机构、用户、角色、组的管理接口.")
                  .version("v1.0")
                 // .contact(new Contact("平台开发组", "www.javafree.com", "gwz126@126.com"))
                 // .licenseUrl("https://www.apache.org/licenses/")
                 // .license("Apache License Version 2.0")
                 // .termsOfServiceUrl("平台开发组")
                  .build();
    }

}