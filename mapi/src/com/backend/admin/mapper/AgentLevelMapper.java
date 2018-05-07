package com.backend.admin.mapper;

import com.api.goods.entity.AgentLevel;

import java.util.List;

public interface AgentLevelMapper {

    List<AgentLevel> listAgentLevelByBrandId(int brandId);

    int removeAgentLevelByBrandId(int brandId);

    int saveAgentLevelByBrandId(List<AgentLevel> agents);
}
