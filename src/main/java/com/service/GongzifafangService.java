package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.GongzifafangEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.GongzifafangVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.GongzifafangView;


/**
 * 工资发放
 *
 * @author 
 * @email 
 * @date 2025-04-13 19:13:19
 */
public interface GongzifafangService extends IService<GongzifafangEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<GongzifafangVO> selectListVO(Wrapper<GongzifafangEntity> wrapper);
   	
   	GongzifafangVO selectVO(@Param("ew") Wrapper<GongzifafangEntity> wrapper);
   	
   	List<GongzifafangView> selectListView(Wrapper<GongzifafangEntity> wrapper);
   	
   	GongzifafangView selectView(@Param("ew") Wrapper<GongzifafangEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<GongzifafangEntity> wrapper);

   	

}

