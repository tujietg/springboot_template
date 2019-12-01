package com.tujietg.template.service;

import com.tujietg.template.mbg.model.PmsBrand;

import java.util.List;

/**
 * @author tujietg
 * @date 2019/12/1 1:35 PM
 */
public interface PmsBrandService {

    List<PmsBrand> listAllBrand();

    int createBrand(PmsBrand brand);

    int updateBrand(Long id, PmsBrand brand);

    int deleteBrand(Long id);

    List<PmsBrand> listBrand(int pageNum, int pageSize);

    PmsBrand getBrand(Long id);

}
