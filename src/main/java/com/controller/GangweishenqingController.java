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

import com.entity.GangweishenqingEntity;
import com.entity.view.GangweishenqingView;

import com.service.GangweishenqingService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 岗位申请
 * 后端接口
 * @author 
 * @email 
 * @date 2025-04-13 19:13:19
 */
@RestController
@RequestMapping("/gangweishenqing")
public class GangweishenqingController {
    @Autowired
    private GangweishenqingService gangweishenqingService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,GangweishenqingEntity gangweishenqing,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("yonggongdanwei")) {
			gangweishenqing.setDanweizhanghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("xuesheng")) {
			gangweishenqing.setXueshengzhanghao((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        EntityWrapper<GangweishenqingEntity> ew = new EntityWrapper<GangweishenqingEntity>();


        //查询结果
		PageUtils page = gangweishenqingService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, gangweishenqing), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,GangweishenqingEntity gangweishenqing, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<GangweishenqingEntity> ew = new EntityWrapper<GangweishenqingEntity>();

        //查询结果
		PageUtils page = gangweishenqingService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, gangweishenqing), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( GangweishenqingEntity gangweishenqing){
       	EntityWrapper<GangweishenqingEntity> ew = new EntityWrapper<GangweishenqingEntity>();
      	ew.allEq(MPUtil.allEQMapPre( gangweishenqing, "gangweishenqing")); 
        return R.ok().put("data", gangweishenqingService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(GangweishenqingEntity gangweishenqing){
        EntityWrapper< GangweishenqingEntity> ew = new EntityWrapper< GangweishenqingEntity>();
 		ew.allEq(MPUtil.allEQMapPre( gangweishenqing, "gangweishenqing")); 
		GangweishenqingView gangweishenqingView =  gangweishenqingService.selectView(ew);
		return R.ok("查询岗位申请成功").put("data", gangweishenqingView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        GangweishenqingEntity gangweishenqing = gangweishenqingService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(gangweishenqing,deSens);
        return R.ok().put("data", gangweishenqing);
    }

    /**
     * 前台详情
     */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        GangweishenqingEntity gangweishenqing = gangweishenqingService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(gangweishenqing,deSens);
        return R.ok().put("data", gangweishenqing);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增岗位申请") 
    public R save(@RequestBody GangweishenqingEntity gangweishenqing, HttpServletRequest request){
        //ValidatorUtils.validateEntity(gangweishenqing);
        gangweishenqingService.insert(gangweishenqing);
        return R.ok().put("data",gangweishenqing.getId());
    }
    
    /**
     * 前台保存
     */
    @SysLog("新增岗位申请")
    @RequestMapping("/add")
    public R add(@RequestBody GangweishenqingEntity gangweishenqing, HttpServletRequest request){
        //ValidatorUtils.validateEntity(gangweishenqing);
        gangweishenqingService.insert(gangweishenqing);
        return R.ok().put("data",gangweishenqing.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改岗位申请")
    public R update(@RequestBody GangweishenqingEntity gangweishenqing, HttpServletRequest request){
        //ValidatorUtils.validateEntity(gangweishenqing);
        //全部更新
        gangweishenqingService.updateById(gangweishenqing);
        return R.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/shBatch")
    @Transactional
    @SysLog("审核岗位申请")
    public R update(@RequestBody Long[] ids, @RequestParam String sfsh, @RequestParam String shhf){
        List<GangweishenqingEntity> list = new ArrayList<GangweishenqingEntity>();
        for(Long id : ids) {
            GangweishenqingEntity gangweishenqing = gangweishenqingService.selectById(id);
            gangweishenqing.setSfsh(sfsh);
            gangweishenqing.setShhf(shhf);
            list.add(gangweishenqing);
        }
        gangweishenqingService.updateBatchById(list);
        return R.ok();
    }


    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除岗位申请")
    public R delete(@RequestBody Long[] ids){
        gangweishenqingService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    








}
