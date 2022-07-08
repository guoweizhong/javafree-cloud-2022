package com.javafree.cloud.admin.service;

import com.javafree.cloud.admin.entity.Tenant;
import com.javafree.cloud.common.api.PageParam;
import com.javafree.cloud.common.api.PageResult;

import java.util.List;

/**
 * @version V1.0
 * @Description: Tenant Service 接口
 * @Author gwz  gwz126@126.com
 * @Date 2022/6/7 15:17
 */

public interface TenantService {
    Tenant saveTenant(Tenant tenant);

    void deleteTenantByIds(List<String> ids);

    void deleteTenant(String id);

    Tenant getTenantById(String id);

    Tenant getTenantByCode(String code);

    PageResult<Tenant> findTenantsByName(String name, PageParam pageParam);

    PageResult<Tenant> findTenantsByTenant(Tenant tenant, PageParam pageParam);

    PageResult<Tenant> findTenantsByTenantAny(Tenant tenant, PageParam pageParam);
}
