package com.dao;

import com.entity.YonggongdanweiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.YonggongdanweiVO;
import com.entity.view.YonggongdanweiView;


/**
 * 用工单位
 * 
 * @author 
 * @email 
 * @date 2025-04-13 19:13:18
 */
public interface YonggongdanweiDao extends BaseMapper<YonggongdanweiEntity> {
	
	List<YonggongdanweiVO> selectListVO(@Param("ew") Wrapper<YonggongdanweiEntity> wrapper);
	
	YonggongdanweiVO selectVO(@Param("ew") Wrapper<YonggongdanweiEntity> wrapper);
	
	List<YonggongdanweiView> selectListView(@Param("ew") Wrapper<YonggongdanweiEntity> wrapper);

	List<YonggongdanweiView> selectListView(Pagination page,@Param("ew") Wrapper<YonggongdanweiEntity> wrapper);

	
	YonggongdanweiView selectView(@Param("ew") Wrapper<YonggongdanweiEntity> wrapper);
	

}
