package com.api.goods.entity;

import java.util.List;

public class HotBrandResult {
    private Brand brand;

    private List<AgentLevel> agentLevels;

    private List<BrandImagesResult> brandImagesResults;

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public List<AgentLevel> getAgentLevels() {
        return agentLevels;
    }

    public void setAgentLevels(List<AgentLevel> agentLevels) {
        this.agentLevels = agentLevels;
    }

    public List<BrandImagesResult> getBrandImagesResults() {
        return brandImagesResults;
    }

    public void setBrandImagesResults(List<BrandImagesResult> brandImagesResults) {
        this.brandImagesResults = brandImagesResults;
    }
}
