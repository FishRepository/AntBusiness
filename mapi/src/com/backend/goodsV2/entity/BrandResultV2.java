package com.backend.goodsV2.entity;

import com.api.common.entity.Result;

import java.util.List;

public class BrandResultV2 extends Result {

    private List<BrandAndAgentLevelV2> list;

    public List<BrandAndAgentLevelV2> getList() {
        return list;
    }

    public void setList(List<BrandAndAgentLevelV2> list) {
        this.list = list;
    }
}
