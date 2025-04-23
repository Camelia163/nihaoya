package com.dao;

import com.entity.PingjiaxueshengEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.PingjiaxueshengVO;
import com.entity.view.PingjiaxueshengView;


/**
 * 评价学生
 * 
 * @author 
 * @email 
 * @date 2025-04-13 19:13:19
 */
public interface PingjiaxueshengDao extends BaseMapper<PingjiaxueshengEntity> {
	
	List<PingjiaxueshengVO> selectListVO(@Param("ew") Wrapper<PingjiaxueshengEntity> wrapper);
	
	PingjiaxueshengVO selectVO(@Param("ew") Wrapper<PingjiaxueshengEntity> wrapper);
	
	List<PingjiaxueshengView> selectListView(@Param("ew") Wrapper<PingjiaxueshengEntity> wrapper);

	List<PingjiaxueshengView> selectListView(Pagination page,@Param("ew") Wrapper<PingjiaxueshengEntity> wrapper);

	
	PingjiaxueshengView selectView(@Param("ew") Wrapper<PingjiaxueshengEntity> wrapper);
	

}
