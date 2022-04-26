package com.javafree.cloud.admin.service;

import com.javafree.cloud.admin.entity.Tenant;
import com.javafree.cloud.admin.entity.User;
import com.javafree.cloud.common.api.PageParam;
import com.javafree.cloud.common.api.PageResult;

import java.util.List;

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
