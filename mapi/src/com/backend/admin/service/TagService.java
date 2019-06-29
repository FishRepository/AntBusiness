package com.backend.admin.service;

import com.api.order.entity.Order;
import com.api.order.mapper.OrderMapper;
import com.backend.admin.entity.Tag;
import com.backend.admin.mapper.TagMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (Tag)表服务接口
 *
 * @author makejava
 * @since 2019-06-25 21:29:44
 */
@Service
public class TagService {

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private OrderMapper orderMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param tagId 主键
     * @return 实例对象
     */
    public Tag queryById(Integer tagId){
        return tagMapper.queryById(tagId);
    }

    public List<Tag> queryAll(Tag tag){
        return tagMapper.queryAll(tag);
    }

    /**
     * 新增数据
     *
     * @param tag 实例对象
     * @return 实例对象
     */
    public Tag insert(Tag tag){
        int insert = tagMapper.insert(tag);
        if(insert>0){
            return tag;
        }
        return null;
    }

    public Tag saveTag(Tag tag) {
        if(StringUtils.isBlank(tag.getTag_name())||StringUtils.isBlank(tag.getTag_color())
        ||tag.getOrder_type()==null||tag.getAccount_id()==null){
            return null;
        }
        return insert(tag);
    }

    public Boolean saveOrderTag(Order order) {
        if(order.getTag_id()!=null){
            Tag tag = queryById(order.getTag_id());
            if(tag==null){
                return false;
            }
            order.setTag_name(tag.getTag_name());
            order.setTag_color(tag.getTag_color());
            int updateTag = orderMapper.updateTag(order);
            if(updateTag>0){
                return true;
            }return false;
        }else{
            if(StringUtils.isBlank(order.getOrder_remark())){
                return false;
            }
            int updateTag = orderMapper.updateTag(order);
            if(updateTag>0){
                return true;
            }return false;
        }
    }
}