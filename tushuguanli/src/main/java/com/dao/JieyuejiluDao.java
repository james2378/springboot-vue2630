package com.dao;

import com.entity.JieyuejiluEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.JieyuejiluView;

/**
 * 借阅记录 Dao 接口
 *
 * @author 
 */
public interface JieyuejiluDao extends BaseMapper<JieyuejiluEntity> {

   List<JieyuejiluView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
