package com.tujietg.template.dao;

import com.tujietg.template.mbg.model.UmsPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 后台用户和角色管理自定义dao
 *
 * @author tujietg
 * @date 2019/12/3 9:47 PM
 */
public interface UmsAdminRoleRelationDao {

    /**
     * 获取用户所有权限(包括+-权限)
     */
    List<UmsPermission> getPermissionList(@Param("adminId") Long adminId);

}
