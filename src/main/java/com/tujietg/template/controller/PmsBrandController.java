package com.tujietg.template.controller;

import com.tujietg.template.common.api.CommonResult;
import com.tujietg.template.mbg.model.PmsBrand;
import com.tujietg.template.service.PmsBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tujietg
 * @date 2019/12/1 1:54 PM
 */
@Controller
@RequestMapping(value = "/brand")
public class PmsBrandController {

    @Autowired
    PmsBrandService pmsBrandService;

    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsBrand>> listAllBrand() {
        return CommonResult.success(pmsBrandService.listAllBrand());
    }


    @RequestMapping(value = "/createBrand", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult createBrand(@RequestBody PmsBrand pmsBrand) {
        CommonResult commonResult;
        int count = pmsBrandService.createBrand(pmsBrand);
        if (count == 1) {
            commonResult = CommonResult.success(pmsBrand);
        } else {
            commonResult = CommonResult.failed("操作失败");
        }
        return commonResult;
    }


    @RequestMapping(value = "/update/{id}")
    @ResponseBody
    public CommonResult updateBrand(@PathVariable("id") Long id, @RequestBody PmsBrand pmsBrand) {
        CommonResult commonResult;
        int count = pmsBrandService.updateBrand(id, pmsBrand);
        if (count == 1) {
            commonResult = CommonResult.success(pmsBrand);
        } else {
            commonResult = CommonResult.failed("操作失败");
        }
        return commonResult;
    }

    @RequestMapping(value = "/delete/{id}")
    @ResponseBody
    public CommonResult deleteBrand(@PathVariable("id") Long id) {
        int count = pmsBrandService.deleteBrand(id);
        if (count == 1) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed("操作失败");
        }
    }


    @RequestMapping(value = "/{id}")
    public CommonResult<PmsBrand> getBrand(@PathVariable("id") long id) {
        return CommonResult.success(pmsBrandService.getBrand(id));
    }
}
