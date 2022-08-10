package com.javafree.cloud.admin.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @version V1.0
 * @Description:修改SpringDoc swagger默认配置
 * @Author gwz  gwz126@126.com
 * @Date 2022/7/29 10:44
 */
@Configuration
public class SpringDocConfig {
  /**
   * 将GroupedOpenApi注入容器中，配置扫描路径和分组名等信息
   * @return
   */
  @Bean
  public GroupedOpenApi publicGroup(@Value("${springdoc.version}") String appVersion) {
    return GroupedOpenApi.builder()
            .group("adminApi")
            //.packagesToScan("com.javafree.cloud")
            .addOpenApiCustomiser(openApi -> openApi.info(new Info().title("组织管理API")
                    .description("组织管理相关API，包括租户、机构、用户、角色、组等管理接口.")
                    .version(appVersion)
                    .license(new License().name("Apache 2.0")
                            .url("https://github.com/guoweizhong/javafree-cloud-2022"))))
            .build();
  }

  /**
   * 配置swagger相关标题、版本等参数
   * @return
   */
  @Bean
  public OpenAPI javafreeOpenAPI() {
    return new OpenAPI()
            .info(new Info().title("组织机构API文档")
                    .description("组织机构管理相关API，包括机构、用户、角色、组的管理接口.")
                    .version("v1.0.0")
                    .license(new License().name("Apache 2.0")
                            .url("https://github.com/guoweizhong/javafree-cloud-2022")))
            .externalDocs(new ExternalDocumentation()
                    .description("平台开发组 gwz126@126.com")
                    .url("http://www.javafree.com"));
  }
}
