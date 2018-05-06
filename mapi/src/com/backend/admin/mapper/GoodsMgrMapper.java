package com.backend.admin.mapper;

import com.backend.admin.entity.Goods;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author
 * @since 2018-05-05
 */
public interface GoodsMgrMapper extends BaseMapper<Goods> {

    List<Goods> listGoods(Long brandId);

    Goods getGoodsById(int id);

    void saveGoods(Goods goods);

    int updateGoods(Goods goods);

    int removeGoodsById(int id);
}
