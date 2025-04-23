package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.dao.PingjiagangweiDao;
import com.entity.PingjiagangweiEntity;
import com.service.PingjiagangweiService;
import com.entity.vo.PingjiagangweiVO;
import com.entity.view.PingjiagangweiView;

@Service("pingjiagangweiService")
public class PingjiagangweiServiceImpl extends ServiceImpl<PingjiagangweiDao, PingjiagangweiEntity> implements PingjiagangweiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<PingjiagangweiEntity> page = this.selectPage(
                new Query<PingjiagangweiEntity>(params).getPage(),
                new EntityWrapper<PingjiagangweiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<PingjiagangweiEntity> wrapper) {
		  Page<PingjiagangweiView> page =new Query<PingjiagangweiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<PingjiagangweiVO> selectListVO(Wrapper<PingjiagangweiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public PingjiagangweiVO selectVO(Wrapper<PingjiagangweiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<PingjiagangweiView> selectListView(Wrapper<PingjiagangweiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public PingjiagangweiView selectView(Wrapper<PingjiagangweiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
