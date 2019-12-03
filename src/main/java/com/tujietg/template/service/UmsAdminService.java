package com.tujietg.template.service;

import com.tujietg.template.mbg.model.UmsAdmin;
import com.tujietg.template.mbg.model.UmsPermission;

import java.util.List;

/**
 * 后台管理员 service
 *
 * @author tujietg
 * @date 2019/12/3 8:49 PM
 */
public interface UmsAdminService {

    /**
     * 获取管理员信息通过username
     *
     * @param username
     * @return
     */
    UmsAdmin getAdminByUsername(String username);

    /**
     * 管理员注册模块
     *
     * @param umsAdminParam
     * @return
     */
    UmsAdmin register(UmsAdmin umsAdminParam);


    /**
     * 管理员登录模块
     *
     * @param username
     * @param password
     * @return
     */
    String login(String username, String password);


    /**
     * 获取用户所有权限（包括角色权限和+-权限）
     */
    List<UmsPermission> getPermissionList(Long adminId);

}
