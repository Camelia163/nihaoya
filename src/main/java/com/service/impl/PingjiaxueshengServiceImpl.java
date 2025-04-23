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


import com.dao.PingjiaxueshengDao;
import com.entity.PingjiaxueshengEntity;
import com.service.PingjiaxueshengService;
import com.entity.vo.PingjiaxueshengVO;
import com.entity.view.PingjiaxueshengView;

@Service("pingjiaxueshengService")
public class PingjiaxueshengServiceImpl extends ServiceImpl<PingjiaxueshengDao, PingjiaxueshengEntity> implements PingjiaxueshengService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<PingjiaxueshengEntity> page = this.selectPage(
                new Query<PingjiaxueshengEntity>(params).getPage(),
                new EntityWrapper<PingjiaxueshengEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<PingjiaxueshengEntity> wrapper) {
		  Page<PingjiaxueshengView> page =new Query<PingjiaxueshengView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<PingjiaxueshengVO> selectListVO(Wrapper<PingjiaxueshengEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public PingjiaxueshengVO selectVO(Wrapper<PingjiaxueshengEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<PingjiaxueshengView> selectListView(Wrapper<PingjiaxueshengEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public PingjiaxueshengView selectView(Wrapper<PingjiaxueshengEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
