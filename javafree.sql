/*
SQLyog Ultimate v12.4.1 (64 bit)
MySQL - 8.0.25 : Database - javafree8
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`javafree8` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `javafree8`;

/*Table structure for table `oauth2_authorization` */

DROP TABLE IF EXISTS `oauth2_authorization`;

CREATE TABLE `oauth2_authorization` (
  `id` varchar(100) COLLATE utf8_bin NOT NULL,
  `registered_client_id` varchar(100) COLLATE utf8_bin NOT NULL,
  `principal_name` varchar(200) COLLATE utf8_bin NOT NULL,
  `authorization_grant_type` varchar(100) COLLATE utf8_bin NOT NULL,
  `attributes` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `state` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `authorization_code_value` blob,
  `authorization_code_issued_at` timestamp NULL DEFAULT NULL,
  `authorization_code_expires_at` timestamp NULL DEFAULT NULL,
  `authorization_code_metadata` varchar(2000) COLLATE utf8_bin DEFAULT NULL,
  `access_token_value` blob,
  `access_token_issued_at` timestamp NULL DEFAULT NULL,
  `access_token_expires_at` timestamp NULL DEFAULT NULL,
  `access_token_metadata` varchar(2000) COLLATE utf8_bin DEFAULT NULL,
  `access_token_type` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `access_token_scopes` varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  `oidc_id_token_value` blob,
  `oidc_id_token_issued_at` timestamp NULL DEFAULT NULL,
  `oidc_id_token_expires_at` timestamp NULL DEFAULT NULL,
  `oidc_id_token_metadata` varchar(2000) COLLATE utf8_bin DEFAULT NULL,
  `refresh_token_value` blob,
  `refresh_token_issued_at` timestamp NULL DEFAULT NULL,
  `refresh_token_expires_at` timestamp NULL DEFAULT NULL,
  `refresh_token_metadata` varchar(2000) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;

/*Data for the table `oauth2_authorization` */

insert  into `oauth2_authorization`(`id`,`registered_client_id`,`principal_name`,`authorization_grant_type`,`attributes`,`state`,`authorization_code_value`,`authorization_code_issued_at`,`authorization_code_expires_at`,`authorization_code_metadata`,`access_token_value`,`access_token_issued_at`,`access_token_expires_at`,`access_token_metadata`,`access_token_type`,`access_token_scopes`,`oidc_id_token_value`,`oidc_id_token_issued_at`,`oidc_id_token_expires_at`,`oidc_id_token_metadata`,`refresh_token_value`,`refresh_token_issued_at`,`refresh_token_expires_at`,`refresh_token_metadata`) values 
('0ec57f8c-82ef-4b12-a29b-e5196ebd4930','javafree','Dept','authorization_code','{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"java.security.Principal\":{\"@class\":\"org.springframework.security.authentication.DeptnamePasswordAuthenticationToken\",\"authorities\":[\"java.util.Collections$UnmodifiableRandomAccessList\",[{\"@class\":\"org.springframework.security.core.authority.SimpleGrantedAuthority\",\"authority\":\"ROLE_Dept\"}]],\"details\":{\"@class\":\"org.springframework.security.web.authentication.WebAuthenticationDetails\",\"remoteAddress\":\"127.0.0.1\",\"sessionId\":\"iODfHUH11zhescCAdxK1omL_sXT7PBcYRKKFMv73\"},\"authenticated\":true,\"principal\":{\"@class\":\"org.springframework.security.core.Deptdetails.Dept\",\"password\":null,\"Deptname\":\"Dept\",\"authorities\":[\"java.util.Collections$UnmodifiableSet\",[{\"@class\":\"org.springframework.security.core.authority.SimpleGrantedAuthority\",\"authority\":\"ROLE_Dept\"}]],\"accountNonExpired\":true,\"accountNonLocked\":true,\"credentialsNonExpired\":true,\"enabled\":true},\"credentials\":null},\"org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest\":{\"@class\":\"org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest\",\"authorizationUri\":\"http://localhost:9000/oauth2/authorize\",\"authorizationGrantType\":{\"value\":\"authorization_code\"},\"responseType\":{\"value\":\"code\"},\"clientId\":\"javafree\",\"redirectUri\":\"https://pig4cloud.com\",\"scopes\":[\"java.util.Collections$UnmodifiableSet\",[]],\"state\":null,\"additionalParameters\":{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"client_secret\":\"javafree\"},\"authorizationRequestUri\":\"http://localhost:9000/oauth2/authorize?response_type=code&client_id=javafree&redirect_uri=https://pig4cloud.com&client_secret=javafree\",\"attributes\":{\"@class\":\"java.util.Collections$UnmodifiableMap\"}},\"state\":\"_MmEaBTCYZC6mwsYJJT4hw8ERJfVH6EuxHW51f9ylDo=\"}','_MmEaBTCYZC6mwsYJJT4hw8ERJfVH6EuxHW51f9ylDo=',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
('75f25ea5-0026-455c-8d8e-6dd8fc80e08d','javafree','Dept','authorization_code','{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"java.security.Principal\":{\"@class\":\"org.springframework.security.authentication.DeptnamePasswordAuthenticationToken\",\"authorities\":[\"java.util.Collections$UnmodifiableRandomAccessList\",[{\"@class\":\"org.springframework.security.core.authority.SimpleGrantedAuthority\",\"authority\":\"ROLE_Dept\"}]],\"details\":{\"@class\":\"org.springframework.security.web.authentication.WebAuthenticationDetails\",\"remoteAddress\":\"0:0:0:0:0:0:0:1\",\"sessionId\":\"Dsp0Ta5oSOFKpl29vGf_DNw9QY4oGVCqR2d0prdc\"},\"authenticated\":true,\"principal\":{\"@class\":\"org.springframework.security.core.Deptdetails.Dept\",\"password\":null,\"Deptname\":\"Dept\",\"authorities\":[\"java.util.Collections$UnmodifiableSet\",[{\"@class\":\"org.springframework.security.core.authority.SimpleGrantedAuthority\",\"authority\":\"ROLE_Dept\"}]],\"accountNonExpired\":true,\"accountNonLocked\":true,\"credentialsNonExpired\":true,\"enabled\":true},\"credentials\":null},\"org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest\":{\"@class\":\"org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest\",\"authorizationUri\":\"http://localhost:9000/oauth2/authorize\",\"authorizationGrantType\":{\"value\":\"authorization_code\"},\"responseType\":{\"value\":\"code\"},\"clientId\":\"javafree\",\"redirectUri\":\"https://pig4cloud.com\",\"scopes\":[\"java.util.Collections$UnmodifiableSet\",[]],\"state\":\"somestate\",\"additionalParameters\":{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"client_secret\":\"javafree\"},\"authorizationRequestUri\":\"http://localhost:9000/oauth2/authorize?response_type=code&client_id=javafree&state=somestate&redirect_uri=https://pig4cloud.com&client_secret=javafree\",\"attributes\":{\"@class\":\"java.util.Collections$UnmodifiableMap\"}},\"state\":\"iMbtFOkB1rB-KNrdamPnLJZ9YV_1bdihNYvtrx1ic-k=\"}','iMbtFOkB1rB-KNrdamPnLJZ9YV_1bdihNYvtrx1ic-k=',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `oauth2_authorization_consent` */

DROP TABLE IF EXISTS `oauth2_authorization_consent`;

CREATE TABLE `oauth2_authorization_consent` (
  `registered_client_id` varchar(100) COLLATE utf8_bin NOT NULL,
  `principal_name` varchar(200) COLLATE utf8_bin NOT NULL,
  `authorities` varchar(1000) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`registered_client_id`,`principal_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;

/*Data for the table `oauth2_authorization_consent` */

/*Table structure for table `oauth2_registered_client` */

DROP TABLE IF EXISTS `oauth2_registered_client`;

CREATE TABLE `oauth2_registered_client` (
  `id` varchar(100) COLLATE utf8_bin NOT NULL,
  `client_id` varchar(100) COLLATE utf8_bin NOT NULL,
  `client_id_issued_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `client_secret` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `client_secret_expires_at` timestamp NULL DEFAULT NULL,
  `client_name` varchar(200) COLLATE utf8_bin NOT NULL,
  `client_authentication_methods` varchar(1000) COLLATE utf8_bin NOT NULL,
  `authorization_grant_types` varchar(1000) COLLATE utf8_bin NOT NULL,
  `redirect_uris` varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  `scopes` varchar(1000) COLLATE utf8_bin NOT NULL,
  `client_settings` varchar(2000) COLLATE utf8_bin NOT NULL,
  `token_settings` varchar(2000) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;

/*Data for the table `oauth2_registered_client` */

insert  into `oauth2_registered_client`(`id`,`client_id`,`client_id_issued_at`,`client_secret`,`client_secret_expires_at`,`client_name`,`client_authentication_methods`,`authorization_grant_types`,`redirect_uris`,`scopes`,`client_settings`,`token_settings`) values 
('8b84f51a-48bc-4200-a912-dfedc967e613','auth-client','2021-09-12 17:32:35','{bcrypt}$2a$10$TgCYeUdv9ZEN0cCzcpJglO3Qqf8jfqjHqIA0EjDFKwi15GEhRitSu',NULL,'8b84f51a-48bc-4200-a912-dfedc967e613','client_secret_basic','refresh_token,client_credentials,authorization_code','https://pig4cloud.com','openid,message.read,message.write','{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.client.require-proof-key\":false,\"settings.client.require-authorization-consent\":true}','{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.token.reuse-refresh-tokens\":true,\"settings.token.id-token-signature-algorithm\":[\"org.springframework.security.oauth2.jose.jws.SignatureAlgorithm\",\"RS256\"],\"settings.token.access-token-time-to-live\":[\"java.time.Duration\",3600.000000000],\"settings.token.refresh-token-time-to-live\":[\"java.time.Duration\",259200.000000000]}'),
('d9993414-4395-47f7-8288-e40cc6f65824','auth-client','2021-09-12 15:28:30','{bcrypt}$2a$10$TaMSG2sZIGjveScduWFmUerxbBx5PwPVd/VDCrPLcn/JILOQPYs6W',NULL,'d9993414-4395-47f7-8288-e40cc6f65824','client_secret_basic','refresh_token,client_credentials,authorization_code','http://127.0.0.1:8080/authorized,http://127.0.0.1:8080/login/oauth2/code/messaging-client-oidc','openid,message.read,message.write','{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.client.require-proof-key\":false,\"settings.client.require-authorization-consent\":true}','{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.token.reuse-refresh-tokens\":true,\"settings.token.id-token-signature-algorithm\":[\"org.springframework.security.oauth2.jose.jws.SignatureAlgorithm\",\"RS256\"],\"settings.token.access-token-time-to-live\":[\"java.time.Duration\",300.000000000],\"settings.token.refresh-token-time-to-live\":[\"java.time.Duration\",3600.000000000]}'),
('javafree','javafree','2021-09-12 17:43:15','{bcrypt}$2a$10$HQicqG.6SA3TNZd7mJ3AduhUS6ZhKXmFmjKVmYtnO7bIRhFwoREre',NULL,'javafree','client_secret_basic','refresh_token,client_credentials,authorization_code','https://pig4cloud.com','openid,message.read,message.write','{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.client.require-proof-key\":false,\"settings.client.require-authorization-consent\":true}','{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.token.reuse-refresh-tokens\":true,\"settings.token.id-token-signature-algorithm\":[\"org.springframework.security.oauth2.jose.jws.SignatureAlgorithm\",\"RS256\"],\"settings.token.access-token-time-to-live\":[\"java.time.Duration\",3600.000000000],\"settings.token.refresh-token-time-to-live\":[\"java.time.Duration\",259200.000000000]}');

/*Table structure for table `sys_oauth2_registered_client` */

DROP TABLE IF EXISTS `sys_oauth2_registered_client`;

CREATE TABLE `sys_oauth2_registered_client` (
  `id` varchar(100) COLLATE utf8_bin NOT NULL,
  `client_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '客户端ID（必选项）',
  `client_id_issued_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '签发客户端标识符的时间',
  `client_secret` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '客户端密码（必选项）',
  `client_secret_expires_at` timestamp NULL DEFAULT NULL COMMENT '如果发出“client_secret”,则需要。客户端密将过期的时间，如果不会过期，则为 0',
  `client_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '客户端名称',
  `client_authentication_methods` varchar(1000) COLLATE utf8_bin NOT NULL,
  `authorization_grant_types` varchar(1000) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '授权类型，且必须为固定值：授权码(code)隐藏式(implicit)密码式(password)客户端凭证(client_credentials)',
  `redirect_uris` varchar(1000) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '授权后返回的地址（必选项）',
  `scopes` varchar(1000) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '令牌的作用范围（可选项）',
  `client_settings` varchar(2000) COLLATE utf8_bin NOT NULL,
  `token_settings` varchar(2000) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;

/*Data for the table `sys_oauth2_registered_client` */

/*Table structure for table `sys_org_dept` */

DROP TABLE IF EXISTS `sys_org_dept`;

CREATE TABLE `sys_org_dept` (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `parent_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '父机构ID',
  `dept_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '机构/部门名称',
  `dept_order` int DEFAULT '0' COMMENT '排序',
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '描述',
  `org_category` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '1' COMMENT '机构类别 1行政机构(默认)，2党组机构，3其他可扩展',
  `org_type` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '机构类型 1一级部门 2子部门',
  `org_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '机构编码',
  `memo` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `status` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '状态（1启用，0不启用）',
  `del_flag` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '删除状态（0，正常，1已删除）',
  `create_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `update_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新日期',
  `ext_data` longtext CHARACTER SET utf8 COLLATE utf8_bin COMMENT '用于字段扩展，可用json格式',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uniq_dept_org_code` (`org_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='组织机构表';

/*Data for the table `sys_org_dept` */

/*Table structure for table `sys_org_dept_Dept_role` */

DROP TABLE IF EXISTS `sys_org_dept_Dept_role`;

CREATE TABLE `sys_org_dept_Dept_role` (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `dept_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '机构部门ID',
  `Dept_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户ID',
  `role_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色ID',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '关联名称，由(用户名：机构名：角色名,用：分隔)组成',
  `rel_order` int DEFAULT '0' COMMENT '排序',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '描述',
  `create_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uniq_sys_group_rel_id` (`dept_id`,`Dept_id`,`role_id`) COMMENT '组ID和成员对象ID唯一',
  KEY `idx_sr_dept_id` (`dept_id`) USING BTREE,
  KEY `idx_sr_Dept_id` (`Dept_id`) USING BTREE,
  KEY `idx_sr_role_id` (`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='机构用户角色关联表';

/*Data for the table `sys_org_dept_Dept_role` */

/*Table structure for table `sys_org_group` */

DROP TABLE IF EXISTS `sys_org_group`;

CREATE TABLE `sys_org_group` (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `group_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '群组名称',
  `group_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '群组编码',
  `group_order` int DEFAULT '0' COMMENT '排序',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '描述',
  `create_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uniq_sys_group_code` (`group_code`) USING BTREE,
  KEY `idx_sr_group_code` (`group_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='群组表';

/*Data for the table `sys_org_group` */

/*Table structure for table `sys_org_group_rel` */

DROP TABLE IF EXISTS `sys_org_group_rel`;

CREATE TABLE `sys_org_group_rel` (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `group_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '群组ID',
  `obj_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '群组关联对象ID',
  `obj_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '群组关联对象类型，1.机构部门(DEPT),2.用户(Dept),3.角色(ROLE)',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '关联名称，由(组名+关联对象名,用||分隔)名组成',
  `rel_order` int DEFAULT '0' COMMENT '排序',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '描述',
  `create_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uniq_sys_group_rel_id` (`group_id`,`obj_id`) COMMENT '组ID和成员对象ID唯一',
  KEY `idx_sr_group_id` (`group_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='群组与成员关联表';

/*Data for the table `sys_org_group_rel` */

/*Table structure for table `sys_org_role` */

DROP TABLE IF EXISTS `sys_org_role`;

CREATE TABLE `sys_org_role` (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `role_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `role_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色编码',
  `role_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色类型，1默认，2职称、3岗位',
  `role_order` int DEFAULT '0' COMMENT '排序',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '描述',
  `create_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uniq_sys_role_code` (`role_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='角色表';

/*Data for the table `sys_org_role` */

insert  into `sys_org_role`(`id`,`role_name`,`role_code`,`role_type`,`role_order`,`description`,`create_by`,`create_time`,`update_by`,`update_time`) values 
('roleid1234','角色名称','rocode001','机构',0,'机构角色',NULL,NULL,NULL,NULL);

/*Table structure for table `sys_org_tenant` */

DROP TABLE IF EXISTS `sys_org_tenant`;

CREATE TABLE `sys_org_tenant` (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '租户名称',
  `tenant_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '租户编码，值唯一',
  `tenant_order` int DEFAULT '0' COMMENT '排序',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '描述',
  `create_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `status` int DEFAULT NULL COMMENT '状态 1正常 0冻结',
  `ext_data` longtext CHARACTER SET utf8 COLLATE utf8_bin COMMENT '用于字段扩展，可用json格式',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uniq_sys_org_tenant_code` (`tenant_code`) COMMENT '租户编码为唯一数据'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='租户信息表';

/*Data for the table `sys_org_tenant` */

/*Table structure for table `sys_org_Dept` */

DROP TABLE IF EXISTS `sys_org_Dept`;

CREATE TABLE `sys_org_Dept` (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `Deptname` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录账号，唯一',
  `realname` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '真实姓名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `salt` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'md5密码盐',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '头像',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `sex` tinyint(1) DEFAULT NULL COMMENT '性别(0-默认未知,1-男,2-女)',
  `email` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '电子邮件',
  `phone` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '电话',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态(1-正常,2-冻结)',
  `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除状态(0-正常,1-已删除)',
  `work_no` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '工号，唯一键',
  `create_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `Dept_type` tinyint(1) DEFAULT NULL COMMENT '用户类型，可按业务需要扩展（1普通用户 2管理员...）',
  `ext_data` longtext CHARACTER SET utf8 COLLATE utf8_bin COMMENT '用于字段扩展，可用json格式',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uniq_sys_Dept_Deptname` (`Deptname`) USING BTREE,
  UNIQUE KEY `uniq_sys_Dept_work_no` (`work_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='用户表';

/*Data for the table `sys_org_Dept` */

insert  into `sys_org_Dept`(`id`,`Deptname`,`realname`,`password`,`salt`,`avatar`,`birthday`,`sex`,`email`,`phone`,`status`,`del_flag`,`work_no`,`create_by`,`create_time`,`update_by`,`update_time`,`Dept_type`,`ext_data`) values
('u1','zhangsan','张三update1','11','1',NULL,'2021-11-23 11:45:26',1,'zs@sina.com','13622222222',1,1,'zhangsan','sang','2021-11-23 11:46:03','san','2021-11-23 11:46:18',1,NULL),
('u11','zhangsan2','张三3','11','1',NULL,'2021-11-23 11:45:26',1,'zs@sina.com','13622222222',1,1,'zhangsan2','sang','2021-11-23 11:46:03','san','2021-11-23 11:46:18',1,NULL),
('u111','zhangsan3','张三4','11','1',NULL,'2021-11-23 11:45:26',1,'zs@sina.com','13622222222',1,1,'zhangsan3','sang','2021-11-23 11:46:03','san','2021-11-23 11:46:18',1,NULL),
('u1111','zhangsan4','张三5','11','1',NULL,'2021-11-23 11:45:26',1,'zs@sina.com','13622222222',1,1,'zhangsan4','sang','2021-11-23 11:46:03','san','2021-11-23 11:46:18',1,NULL),
('u11111','zhangsan5','张三6','11','1',NULL,'2021-11-23 11:45:26',1,'zs@sina.com','13622222222',1,1,'zhangsan5','sang','2021-11-23 11:46:03','san','2021-11-23 11:46:18',1,NULL),
('u111111','zhangsan6','张三7','11','1',NULL,'2021-11-23 11:45:26',1,'zs@sina.com','13622222222',1,1,'zhangsan6','sang','2021-11-23 11:46:03','san','2021-11-23 11:46:18',1,NULL),
('u121','zhangsan8','张三9','11','1',NULL,'2021-11-23 11:45:26',1,'zs@sina.com','13622222222',1,1,'zhangsan8','sang','2021-11-23 11:46:03','san','2021-11-23 11:46:18',1,NULL),
('u1221111','zhangsan7','张三8','11','1',NULL,'2021-11-23 11:45:26',1,'zs@sina.com','13622222222',1,1,'zhangsan7','sang','2021-11-23 11:46:03','san','2021-11-23 11:46:18',1,NULL),
('u132','zhangsan1','张三2','11','1',NULL,'2021-11-23 11:45:26',1,'zs@sina.com','13622222222',1,1,'zhangsan1','sang','2021-11-23 11:46:03','san','2021-11-23 11:46:18',1,NULL),
('u2','lisi','李四','22','1',NULL,'2021-11-16 15:28:17',2,'lisi@sina.com','18777766666',1,0,'lisno','system','2021-11-23 15:29:16','system','2021-11-24 15:29:28',2,NULL),
('u2222','lisi1','李四1','22','1',NULL,'2021-11-16 15:28:17',2,'lisi@sina.com','18777766666',1,0,'lisno1','system','2021-11-23 15:29:16','system','2021-11-24 15:29:28',2,NULL),
('u233','lisi2','李四2','22','1',NULL,'2021-11-16 15:28:17',2,'lisi@sina.com','18777766666',1,0,'lisno2','system','2021-11-23 15:29:16','system','2021-11-24 15:29:28',2,NULL),
('u2333','lisi3','李四3','22','1',NULL,'2021-11-16 15:28:17',2,'lisi@sina.com','18777766666',1,0,'lisno3','system','2021-11-23 15:29:16','system','2021-11-24 15:29:28',2,NULL),
('u23333','lisi4','李四4','22','1',NULL,'2021-11-16 15:28:17',2,'lisi@sina.com','18777766666',1,0,'lisno4','system','2021-11-23 15:29:16','system','2021-11-24 15:29:28',2,NULL),
('u24','lisi6','李四6','22','1',NULL,'2021-11-16 15:28:17',2,'lisi@sina.com','18777766666',1,0,'lisno55','system','2021-11-23 15:29:16','system','2021-11-24 15:29:28',2,NULL),
('u244','lisi7','李四7','22','1',NULL,'2021-11-16 15:28:17',2,'lisi@sina.com','18777766666',1,0,'lisno6','system','2021-11-23 15:29:16','system','2021-11-24 15:29:28',2,NULL),
('u2444','lisi5','李四5','22','1',NULL,'2021-11-16 15:28:17',2,'lisi@sina.com','18777766666',1,0,'lisno5','system','2021-11-23 15:29:16','system','2021-11-24 15:29:28',2,NULL),
('u245','lisi8','李四8','22','1',NULL,'2021-11-16 15:28:17',2,'lisi@sina.com','18777766666',1,0,'lisno7','system','2021-11-23 15:29:16','system','2021-11-24 15:29:28',2,NULL),
('u25','lisi13','李四13','22','1',NULL,'2021-11-16 15:28:17',2,'lisi@sina.com','18777766666',1,0,'lisno224','system','2021-11-23 15:29:16','system','2021-11-24 15:29:28',2,NULL),
('u252','lisi12','李四12','22','1',NULL,'2021-11-16 15:28:17',2,'lisi@sina.com','18777766666',1,0,'lisno123','system','2021-11-23 15:29:16','system','2021-11-24 15:29:28',2,NULL),
('u253','lisi11','李四11','22','1',NULL,'2021-11-16 15:28:17',2,'lisi@sina.com','18777766666',1,0,'lisno111','system','2021-11-23 15:29:16','system','2021-11-24 15:29:28',2,NULL),
('u254','lisi9','李四9','22','1',NULL,'2021-11-16 15:28:17',2,'lisi@sina.com','18777766666',1,0,'lisno8','system','2021-11-23 15:29:16','system','2021-11-24 15:29:28',2,NULL),
('u255556','lisi16','李四44','22','1',NULL,'2021-11-16 15:28:17',2,'lisi@sina.com','18777766666',1,0,'lisno335','system','2021-11-23 15:29:16','system','2021-11-24 15:29:28',2,NULL),
('u25556','lisi15','李四334','22','1',NULL,'2021-11-16 15:28:17',2,'lisi@sina.com','18777766666',1,0,'lisno334','system','2021-11-23 15:29:16','system','2021-11-24 15:29:28',2,NULL),
('u256','lisi14','李四23','22','1',NULL,'2021-11-16 15:28:17',2,'lisi@sina.com','18777766666',1,0,'lisno225','system','2021-11-23 15:29:16','system','2021-11-24 15:29:28',2,NULL),
('u26','lisi1211','李四443','22','1',NULL,'2021-11-16 15:28:17',2,'lisi@sina.com','18777766666',1,0,'lisno432','system','2021-11-23 15:29:16','system','2021-11-24 15:29:28',2,NULL),
('u2655','lisi2113','李四464','22','1',NULL,'2021-11-16 15:28:17',2,'lisi@sina.com','18777766666',1,0,'lisno431','system','2021-11-23 15:29:16','system','2021-11-24 15:29:28',2,NULL),
('u266','lisi171','李四434','22','1',NULL,'2021-11-16 15:28:17',2,'lisi@sina.com','18777766666',1,0,'lisno336','system','2021-11-23 15:29:16','system','2021-11-24 15:29:28',2,NULL),
('u2677','lisi2323','李四21','22','1',NULL,'2021-11-16 15:28:17',2,'lisi@sina.com','18777766666',1,0,'lisno456','system','2021-11-23 15:29:16','system','2021-11-24 15:29:28',2,NULL),
('u2778','lisi2344','李四22','22','1',NULL,'2021-11-16 15:28:17',2,'lisi@sina.com','18777766666',1,0,'lisno546','system','2021-11-23 15:29:16','system','2021-11-24 15:29:28',2,NULL),
('u28','lisi44444','李四232','22','1',NULL,'2021-11-16 15:28:17',2,'lisi@sina.com','18777766666',1,0,'lisno654','system','2021-11-23 15:29:16','system','2021-11-24 15:29:28',2,NULL),
('u288','lisi444','李四444','22','1',NULL,'2021-11-16 15:28:17',2,'lisi@sina.com','18777766666',1,0,'lisno765','system','2021-11-23 15:29:16','system','2021-11-24 15:29:28',2,NULL),
('u2888','lisi55','李四555','22','1',NULL,'2021-11-16 15:28:17',2,'lisi@sina.com','18777766666',1,0,'lisno678','system','2021-11-23 15:29:16','system','2021-11-24 15:29:28',2,NULL),
('u29778','lisi23449','李四22','22','1',NULL,'2021-11-16 15:28:17',2,'lisi@sina.com','18777766666',1,0,'lisno5469','system','2021-11-23 15:29:16','system','2021-11-24 15:29:28',2,NULL),
('u298','lisi444449','李四232','22','1',NULL,'2021-11-16 15:28:17',2,'lisi@sina.com','18777766666',1,0,'lisno6549','system','2021-11-23 15:29:16','system','2021-11-24 15:29:28',2,NULL),
('u92222','lisi19','李四1','22','1',NULL,'2021-11-16 15:28:17',2,'lisi@sina.com','18777766666',1,0,'lisno19','system','2021-11-23 15:29:16','system','2021-11-24 15:29:28',2,NULL),
('u9233','lisi29','李四2','22','1',NULL,'2021-11-16 15:28:17',2,'lisi@sina.com','18777766666',1,0,'lisno29','system','2021-11-23 15:29:16','system','2021-11-24 15:29:28',2,NULL),
('u92333','lisi39','李四3','22','1',NULL,'2021-11-16 15:28:17',2,'lisi@sina.com','18777766666',1,0,'lisno39','system','2021-11-23 15:29:16','system','2021-11-24 15:29:28',2,NULL),
('u923333','lisi49','李四4','22','1',NULL,'2021-11-16 15:28:17',2,'lisi@sina.com','18777766666',1,0,'lisno49','system','2021-11-23 15:29:16','system','2021-11-24 15:29:28',2,NULL),
('u924','lisi69','李四6','22','1',NULL,'2021-11-16 15:28:17',2,'lisi@sina.com','18777766666',1,0,'lisno559','system','2021-11-23 15:29:16','system','2021-11-24 15:29:28',2,NULL),
('u9244','lisi79','李四7','22','1',NULL,'2021-11-16 15:28:17',2,'lisi@sina.com','18777766666',1,0,'lisno69','system','2021-11-23 15:29:16','system','2021-11-24 15:29:28',2,NULL),
('u92444','lisi59','李四5','22','1',NULL,'2021-11-16 15:28:17',2,'lisi@sina.com','18777766666',1,0,'lisno59','system','2021-11-23 15:29:16','system','2021-11-24 15:29:28',2,NULL),
('u9245','lisi89','李四8','22','1',NULL,'2021-11-16 15:28:17',2,'lisi@sina.com','18777766666',1,0,'lisno79','system','2021-11-23 15:29:16','system','2021-11-24 15:29:28',2,NULL),
('u925','lisi139','李四13','22','1',NULL,'2021-11-16 15:28:17',2,'lisi@sina.com','18777766666',1,0,'lisno2249','system','2021-11-23 15:29:16','system','2021-11-24 15:29:28',2,NULL),
('u9252','lisi129','李四12','22','1',NULL,'2021-11-16 15:28:17',2,'lisi@sina.com','18777766666',1,0,'lisno1239','system','2021-11-23 15:29:16','system','2021-11-24 15:29:28',2,NULL),
('u9253','lisi119','李四11','22','1',NULL,'2021-11-16 15:28:17',2,'lisi@sina.com','18777766666',1,0,'lisno1119','system','2021-11-23 15:29:16','system','2021-11-24 15:29:28',2,NULL),
('u9254','lisi99','李四9','22','1',NULL,'2021-11-16 15:28:17',2,'lisi@sina.com','18777766666',1,0,'lisno89','system','2021-11-23 15:29:16','system','2021-11-24 15:29:28',2,NULL),
('u9255556','lisi169','李四44','22','1',NULL,'2021-11-16 15:28:17',2,'lisi@sina.com','18777766666',1,0,'lisno3359','system','2021-11-23 15:29:16','system','2021-11-24 15:29:28',2,NULL),
('u925556','lisi159','李四334','22','1',NULL,'2021-11-16 15:28:17',2,'lisi@sina.com','18777766666',1,0,'lisno3349','system','2021-11-23 15:29:16','system','2021-11-24 15:29:28',2,NULL),
('u9256','lisi149','李四23','22','1',NULL,'2021-11-16 15:28:17',2,'lisi@sina.com','18777766666',1,0,'lisno2259','system','2021-11-23 15:29:16','system','2021-11-24 15:29:28',2,NULL),
('u926','lisi12119','李四443','22','1',NULL,'2021-11-16 15:28:17',2,'lisi@sina.com','18777766666',1,0,'lisno4329','system','2021-11-23 15:29:16','system','2021-11-24 15:29:28',2,NULL),
('u92655','lisi21139','李四464','22','1',NULL,'2021-11-16 15:28:17',2,'lisi@sina.com','18777766666',1,0,'lisno4319','system','2021-11-23 15:29:16','system','2021-11-24 15:29:28',2,NULL),
('u9266','lisi1719','李四434','22','1',NULL,'2021-11-16 15:28:17',2,'lisi@sina.com','18777766666',1,0,'lisno3369','system','2021-11-23 15:29:16','system','2021-11-24 15:29:28',2,NULL),
('u92677','lisi23239','李四21','22','1',NULL,'2021-11-16 15:28:17',2,'lisi@sina.com','18777766666',1,0,'lisno4569','system','2021-11-23 15:29:16','system','2021-11-24 15:29:28',2,NULL),
('u9288','lisi4449','李四444','22','1',NULL,'2021-11-16 15:28:17',2,'lisi@sina.com','18777766666',1,0,'lisno7659','system','2021-11-23 15:29:16','system','2021-11-24 15:29:28',2,NULL),
('u92888','lisi559','李四555','22','1',NULL,'2021-11-16 15:28:17',2,'lisi@sina.com','18777766666',1,0,'lisno6789','system','2021-11-23 15:29:16','system','2021-11-24 15:29:28',2,NULL);

/*Table structure for table `sys_org_Dept2` */

DROP TABLE IF EXISTS `sys_org_Dept2`;

CREATE TABLE `sys_org_Dept2` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL,
  `avatar` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `birthday` datetime(6) DEFAULT NULL,
  `create_by` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `create_time` datetime(6) DEFAULT NULL,
  `del_flag` int DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ext_data` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `realname` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `salt` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `sex` int DEFAULT NULL,
  `status` int DEFAULT NULL,
  `update_by` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `Dept_type` int DEFAULT NULL,
  `Deptname` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;

/*Data for the table `sys_org_Dept2` */

insert  into `sys_org_Dept2`(`id`,`avatar`,`birthday`,`create_by`,`create_time`,`del_flag`,`email`,`ext_data`,`password`,`phone`,`realname`,`salt`,`sex`,`status`,`update_by`,`update_time`,`Dept_type`,`Deptname`) values
('1','1','2021-11-24 21:50:55.000000','1','2021-11-24 21:51:05.000000',1,'1','1','1','1','dfff','1',1,1,'1','2021-11-24 21:51:26.000000',1,'ee');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
