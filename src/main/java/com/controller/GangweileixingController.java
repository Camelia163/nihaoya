package com.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;
import java.lang.*;
import java.math.*;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import com.utils.ValidatorUtils;
import com.utils.DeSensUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;
import com.annotation.SysLog;

import com.entity.GangweileixingEntity;
import com.entity.view.GangweileixingView;

import com.service.GangweileixingService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 岗位类型
 * 后端接口
 * @author 
 * @email 
 * @date 2025-04-13 19:13:18
 */
@RestController
@RequestMapping("/gangweileixing")
public class GangweileixingController {
    @Autowired
    private GangweileixingService gangweileixingService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,GangweileixingEntity gangweileixing,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<GangweileixingEntity> ew = new EntityWrapper<GangweileixingEntity>();


        //查询结果
		PageUtils page = gangweileixingService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, gangweileixing), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,GangweileixingEntity gangweileixing, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<GangweileixingEntity> ew = new EntityWrapper<GangweileixingEntity>();

        //查询结果
		PageUtils page = gangweileixingService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, gangweileixing), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( GangweileixingEntity gangweileixing){
       	EntityWrapper<GangweileixingEntity> ew = new EntityWrapper<GangweileixingEntity>();
      	ew.allEq(MPUtil.allEQMapPre( gangweileixing, "gangweileixing")); 
        return R.ok().put("data", gangweileixingService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(GangweileixingEntity gangweileixing){
        EntityWrapper< GangweileixingEntity> ew = new EntityWrapper< GangweileixingEntity>();
 		ew.allEq(MPUtil.allEQMapPre( gangweileixing, "gangweileixing")); 
		GangweileixingView gangweileixingView =  gangweileixingService.selectView(ew);
		return R.ok("查询岗位类型成功").put("data", gangweileixingView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        GangweileixingEntity gangweileixing = gangweileixingService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(gangweileixing,deSens);
        return R.ok().put("data", gangweileixing);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        GangweileixingEntity gangweileixing = gangweileixingService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(gangweileixing,deSens);
        return R.ok().put("data", gangweileixing);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增岗位类型") 
    public R save(@RequestBody GangweileixingEntity gangweileixing, HttpServletRequest request){
        //ValidatorUtils.validateEntity(gangweileixing);
        gangweileixingService.insert(gangweileixing);
        return R.ok().put("data",gangweileixing.getId());
    }
    
    /**
     * 前台保存
     */
    @SysLog("新增岗位类型")
    @RequestMapping("/add")
    public R add(@RequestBody GangweileixingEntity gangweileixing, HttpServletRequest request){
        //ValidatorUtils.validateEntity(gangweileixing);
        gangweileixingService.insert(gangweileixing);
        return R.ok().put("data",gangweileixing.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改岗位类型")
    public R update(@RequestBody GangweileixingEntity gangweileixing, HttpServletRequest request){
        //ValidatorUtils.validateEntity(gangweileixing);
        //全部更新
        gangweileixingService.updateById(gangweileixing);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除岗位类型")
    public R delete(@RequestBody Long[] ids){
        gangweileixingService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    








}
