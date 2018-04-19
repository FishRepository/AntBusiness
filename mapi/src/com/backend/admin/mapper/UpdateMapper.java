package com.backend.admin.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * @author caojiantao
 */
public interface UpdateMapper {

    /**
     * 更新订单创建时间
     */
    int updateOrderTime(@Param("orderId") int orderId, @Param("createTime") Date createTime);
}
