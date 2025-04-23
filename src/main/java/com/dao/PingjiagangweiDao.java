package com.dao;

import com.entity.PingjiagangweiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.PingjiagangweiVO;
import com.entity.view.PingjiagangweiView;


/**
 * 评价岗位
 * 
 * @author 
 * @email 
 * @date 2025-04-13 19:13:19
 */
public interface PingjiagangweiDao extends BaseMapper<PingjiagangweiEntity> {
	
	List<PingjiagangweiVO> selectListVO(@Param("ew") Wrapper<PingjiagangweiEntity> wrapper);
	
	PingjiagangweiVO selectVO(@Param("ew") Wrapper<PingjiagangweiEntity> wrapper);
	
	List<PingjiagangweiView> selectListView(@Param("ew") Wrapper<PingjiagangweiEntity> wrapper);

	List<PingjiagangweiView> selectListView(Pagination page,@Param("ew") Wrapper<PingjiagangweiEntity> wrapper);

	
	PingjiagangweiView selectView(@Param("ew") Wrapper<PingjiagangweiEntity> wrapper);
	

}
