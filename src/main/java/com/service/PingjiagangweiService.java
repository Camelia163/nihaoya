package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.PingjiagangweiEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.PingjiagangweiVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.PingjiagangweiView;


/**
 * 评价岗位
 *
 * @author 
 * @email 
 * @date 2025-04-13 19:13:19
 */
public interface PingjiagangweiService extends IService<PingjiagangweiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<PingjiagangweiVO> selectListVO(Wrapper<PingjiagangweiEntity> wrapper);
   	
   	PingjiagangweiVO selectVO(@Param("ew") Wrapper<PingjiagangweiEntity> wrapper);
   	
   	List<PingjiagangweiView> selectListView(Wrapper<PingjiagangweiEntity> wrapper);
   	
   	PingjiagangweiView selectView(@Param("ew") Wrapper<PingjiagangweiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<PingjiagangweiEntity> wrapper);

   	

}

