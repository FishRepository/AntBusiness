package com.api.goods.entity;

import com.api.common.entity.Result;

import java.io.Serializable;
import java.util.List;

public class BrandInfo extends Result implements Serializable {

    private static final long serialVersionUID = -5020143685941405612L;

    private Brand brand;

    private List<BrandImagesResult> brandAndImages;

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public List<BrandImagesResult> getBrandAndImages() {
        return brandAndImages;
    }

    public void setBrandAndImages(List<BrandImagesResult> brandAndImages) {
        this.brandAndImages = brandAndImages;
    }
}
