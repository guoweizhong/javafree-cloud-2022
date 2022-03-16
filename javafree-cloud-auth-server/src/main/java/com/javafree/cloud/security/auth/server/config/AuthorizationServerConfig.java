package com.javafree.cloud.security.auth.server.config;

import com.javafree.cloud.security.auth.server.jose.Jwks;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.ClientSettings;
import org.springframework.security.oauth2.server.authorization.config.TokenSettings;
import org.springframework.security.web.SecurityFilterChain;

import java.time.Duration;

/*
 * @Description:
 * @Author gwz  gwz126@126.com
 * @Date 2021/9/11 15:21
 * @version V1.0
 */
@Configuration(proxyBeanMethods = false)
public class AuthorizationServerConfig {

	// security 挂载 SAS 【最重要的一步】
	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE)
	public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
		OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
		return http.formLogin(Customizer.withDefaults()).build();
	}

	/**
	 * 创建客户端信息，可以保存在内存和数据库，此处保存在数据库中
	 * @param jdbcTemplate
	 * @return
	 */
	@Bean
	public RegisteredClientRepository registeredClientRepository(JdbcTemplate jdbcTemplate) {
		RegisteredClient registeredClient = RegisteredClient.withId("javafree")
				// 客户端id 需要唯一
				.clientId("javafree")
				// 客户端密码
				.clientSecret("javafree")
				// 可以基于 basic 的方式和授权服务器进行认证
				.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
				// 授权码
				.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
				// 刷新token
				.authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
				// 客户端模式
				.authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
				// 重定向url
				.redirectUri("https://pig4cloud.com")
				// 客户端申请的作用域，也可以理解这个客户端申请访问用户的哪些信息，比如：获取用户信息，获取用户照片等
				.scope(OidcScopes.OPENID)
				.scope("message.read")
				.scope("message.write")
				// 是否需要用户确认一下客户端需要获取用户的哪些权限
				.clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
				//token 设置
				.tokenSettings(TokenSettings.builder()
						// accessToken 的有效期 1小时
						.accessTokenTimeToLive(Duration.ofHours(1))
						// refreshToken 的有效期 3天
						.refreshTokenTimeToLive(Duration.ofDays(3))
						// 是否可重用刷新令牌
						.reuseRefreshTokens(true)
						.build())
				.build();

		// Save registered client in db as if in-memory
		JdbcRegisteredClientRepository registeredClientRepository = new JdbcRegisteredClientRepository(jdbcTemplate);
		registeredClientRepository.save(registeredClient);

		return registeredClientRepository;
	}

	/**
	 * 保存授权信息，授权服务器给我们颁发来token，那我们肯定需要保存吧，由这个服务来保存
	 */
	@Bean
	public OAuth2AuthorizationService authorizationService(JdbcTemplate jdbcTemplate, RegisteredClientRepository registeredClientRepository) {
		return new JdbcOAuth2AuthorizationService(jdbcTemplate, registeredClientRepository);
	}

	/**
	 * 如果是授权码的流程，可能客户端申请了多个权限，比如：获取用户信息，修改用户信息，此Service处理的是用户给这个客户端哪些权限，比如只给获取用户信息的权限
	 */
	@Bean
	public OAuth2AuthorizationConsentService authorizationConsentService(JdbcTemplate jdbcTemplate, RegisteredClientRepository registeredClientRepository) {
		return new JdbcOAuth2AuthorizationConsentService(jdbcTemplate, registeredClientRepository);
	}

	/**
	 * 对JWT进行签名的 加解密密钥
	 */
	@Bean
	public JWKSource<SecurityContext> jwkSource() {
		RSAKey rsaKey = Jwks.generateRsa();
		JWKSet jwkSet = new JWKSet(rsaKey);
		return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
	}
	/**
	 * 配置一些断点的路径，比如：获取token、授权端点 等
	 */
//	@Bean
//	public ProviderSettings providerSettings() {
//		return ProviderSettings.builder()
//				// 配置获取token的端点路径
//				//.tokenEndpoint()
//				// 发布者的url地址,一般是本系统访问的根路径
//				//.issuer("http://auth-server:9000")
//				.issuer("http://localhost:9000/auth")
//				.build();
//	}



}
