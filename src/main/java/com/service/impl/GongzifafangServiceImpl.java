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


import com.dao.GongzifafangDao;
import com.entity.GongzifafangEntity;
import com.service.GongzifafangService;
import com.entity.vo.GongzifafangVO;
import com.entity.view.GongzifafangView;

@Service("gongzifafangService")
public class GongzifafangServiceImpl extends ServiceImpl<GongzifafangDao, GongzifafangEntity> implements GongzifafangService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<GongzifafangEntity> page = this.selectPage(
                new Query<GongzifafangEntity>(params).getPage(),
                new EntityWrapper<GongzifafangEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<GongzifafangEntity> wrapper) {
		  Page<GongzifafangView> page =new Query<GongzifafangView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<GongzifafangVO> selectListVO(Wrapper<GongzifafangEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public GongzifafangVO selectVO(Wrapper<GongzifafangEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<GongzifafangView> selectListView(Wrapper<GongzifafangEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public GongzifafangView selectView(Wrapper<GongzifafangEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
