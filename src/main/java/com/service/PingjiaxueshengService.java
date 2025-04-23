package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.PingjiaxueshengEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.PingjiaxueshengVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.PingjiaxueshengView;


/**
 * 评价学生
 *
 * @author 
 * @email 
 * @date 2025-04-13 19:13:19
 */
public interface PingjiaxueshengService extends IService<PingjiaxueshengEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<PingjiaxueshengVO> selectListVO(Wrapper<PingjiaxueshengEntity> wrapper);
   	
   	PingjiaxueshengVO selectVO(@Param("ew") Wrapper<PingjiaxueshengEntity> wrapper);
   	
   	List<PingjiaxueshengView> selectListView(Wrapper<PingjiaxueshengEntity> wrapper);
   	
   	PingjiaxueshengView selectView(@Param("ew") Wrapper<PingjiaxueshengEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<PingjiaxueshengEntity> wrapper);

   	

}

