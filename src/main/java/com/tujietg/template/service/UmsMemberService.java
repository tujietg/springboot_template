package com.tujietg.template.service;

import com.tujietg.template.common.api.CommonResult;

/**
 * @author tujietg
 * @date 2019/12/2 8:47 PM
 */
public interface UmsMemberService {

    CommonResult generateAuthCode(String telephone);

    CommonResult verifyAuthCode(String telephone, String AuthCode);

}
