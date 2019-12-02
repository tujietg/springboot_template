package com.tujietg.template.controller;

import com.tujietg.template.common.api.CommonResult;
import com.tujietg.template.service.UmsMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author tujietg
 * @date 2019/12/2 9:14 PM
 */
@Controller
@RequestMapping(value = "/sso")
@Api(tags = "UmsMemberController", description = "会员登录注册管理")
public class UmsMemberController {

    @Autowired
    UmsMemberService memberService;

    @ApiOperation("获取验证码")
    @RequestMapping(value = "/getAuthCode", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult generateAuthCode(String telephone) {
        return memberService.generateAuthCode(telephone);
    }

    @ApiOperation("校验验证码是否正确")
    @RequestMapping(value = "/verifyAuthCode", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult verifyAuthCode(@RequestParam String telephone, @RequestParam String AuthCode) {
        return memberService.verifyAuthCode(telephone, AuthCode);
    }

}
