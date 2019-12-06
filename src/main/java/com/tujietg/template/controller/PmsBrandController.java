package com.tujietg.template.controller;

import com.tujietg.template.common.api.CommonPage;
import com.tujietg.template.common.api.CommonResult;
import com.tujietg.template.mbg.model.PmsBrand;
import com.tujietg.template.service.PmsBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tujietg
 * @date 2019/12/1 1:54 PM
 */
@Controller
@RequestMapping(value = "/brand")
@Api(tags = "PmsBrandController", description = "品牌分类模块")
public class PmsBrandController {

    @Autowired
    PmsBrandService brandService;

    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("获取所有的品牌")
    @PreAuthorize("hasAuthority('pms:brand:read')")
    public CommonResult<List<PmsBrand>> listAllBrand() {
        return CommonResult.success(brandService.listAllBrand());
    }


    @RequestMapping(value = "/createBrand", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("创建品牌")
    @PreAuthorize("hasAuthority('pms:brand:create')")
    public CommonResult createBrand(@RequestBody PmsBrand pmsBrand) {
        CommonResult commonResult;
        int count = brandService.createBrand(pmsBrand);
        if (count == 1) {
            commonResult = CommonResult.success(pmsBrand);
        } else {
            commonResult = CommonResult.failed("操作失败");
        }
        return commonResult;
    }


    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("更新品牌")
    @PreAuthorize("hasAuthority('pms:brand:update')")
    public CommonResult updateBrand(@PathVariable("id") Long id, @RequestBody PmsBrand pmsBrand) {
        CommonResult commonResult;
        int count = brandService.updateBrand(id, pmsBrand);
        if (count == 1) {
            commonResult = CommonResult.success(pmsBrand);
        } else {
            commonResult = CommonResult.failed("操作失败");
        }
        return commonResult;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("根据id删除品牌")
    @PreAuthorize("hasAuthority('pms:brand:delete')")
    public CommonResult deleteBrand(@PathVariable("id") Long id) {
        int count = brandService.deleteBrand(id);
        if (count == 1) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed("操作失败");
        }
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation("根据id获取品牌信息")
    @ResponseBody
    @PreAuthorize("hasAuthority('pms:brand:read')")
    public CommonResult<PmsBrand> getBrand(@PathVariable("id") long id) {
        return CommonResult.success(brandService.getBrand(id));
    }

    @ApiOperation("分页查询品牌列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAuthority('pms:brand:read')")
    public CommonResult<CommonPage<PmsBrand>> listBrand(@RequestParam(value = "pageNum", defaultValue = "1")
                                                        @ApiParam("页码") Integer pageNum,
                                                        @RequestParam(value = "pageSize", defaultValue = "3")
                                                        @ApiParam("每页数量") Integer pageSize) {
        List<PmsBrand> brandList = brandService.listBrand(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(brandList));
    }
}
