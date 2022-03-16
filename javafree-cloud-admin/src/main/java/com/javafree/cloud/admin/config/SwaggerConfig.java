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
import org.springframework.http.HttpMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@Configuration
public class SwaggerConfig {

    /**
     * 将Docket注入容器中，配置扫描路径和分组名等信息
     * @return
     */
    @Bean
    public Docket OrgAPI() {
        return new Docket(DocumentationType.OAS_30)
                .groupName("机构管理相关API")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.javafree.cloud"))
                //.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                //自己定义默认返回状态消息
                .useDefaultResponseMessages(false);
    }

    /**
     * 配置swagger相关标题、版本等参数
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfo(
                "组织机构 API",
                "组织机构管理相关API，包括机构、用户、角色、组的管理接口.",
                "v1.0",
                "平台开发组",
                new Contact("平台开发组", "www.javafree.com", "gwz126@126.com"),
                "Apache License Version 2.0",
                "https://www.apache.org/licenses/", Collections.emptyList());
    }

}