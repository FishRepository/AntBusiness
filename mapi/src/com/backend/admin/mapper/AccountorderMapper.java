package com.backend.admin.mapper;

import com.backend.admin.entity.Accountorder;
import com.backend.admin.entity.OrderListVo;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 
 * @since 2018-04-20
 */
public interface AccountorderMapper extends BaseMapper<Accountorder> {

    List<OrderListVo> selectOrderListBytype(@Param(value = "orderType") int orderType,@Param(value = "accountId") int accountId,
                                            @Param(value = "month") String month,@Param(value = "year") String year);
}
