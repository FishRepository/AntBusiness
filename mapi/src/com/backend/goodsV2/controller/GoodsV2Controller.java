package com.backend.goodsV2.controller;


import com.backend.goodsV2.service.GoodsServiceV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GoodsV2Controller {

    @Autowired
    private GoodsServiceV2 goodsServiceV2;

    @RequestMapping("goodsV2/queryBrandAndGoods")
    public Object queryBrandAndGoods(Integer account_id){

        return goodsServiceV2.queryBrandAndGoods(account_id);
    }
}
