package com.dao;

import com.entity.GongzifafangEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.GongzifafangVO;
import com.entity.view.GongzifafangView;


/**
 * 工资发放
 * 
 * @author 
 * @email 
 * @date 2025-04-13 19:13:19
 */
public interface GongzifafangDao extends BaseMapper<GongzifafangEntity> {
	
	List<GongzifafangVO> selectListVO(@Param("ew") Wrapper<GongzifafangEntity> wrapper);
	
	GongzifafangVO selectVO(@Param("ew") Wrapper<GongzifafangEntity> wrapper);
	
	List<GongzifafangView> selectListView(@Param("ew") Wrapper<GongzifafangEntity> wrapper);

	List<GongzifafangView> selectListView(Pagination page,@Param("ew") Wrapper<GongzifafangEntity> wrapper);

	
	GongzifafangView selectView(@Param("ew") Wrapper<GongzifafangEntity> wrapper);
	

}
