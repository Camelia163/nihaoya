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

import com.entity.PingjiagangweiEntity;
import com.entity.view.PingjiagangweiView;

import com.service.PingjiagangweiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 评价岗位
 * 后端接口
 * @author 
 * @email 
 * @date 2025-04-13 19:13:19
 */
@RestController
@RequestMapping("/pingjiagangwei")
public class PingjiagangweiController {
    @Autowired
    private PingjiagangweiService pingjiagangweiService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,PingjiagangweiEntity pingjiagangwei,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("xuesheng")) {
			pingjiagangwei.setXueshengzhanghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("yonggongdanwei")) {
			pingjiagangwei.setDanweizhanghao((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        EntityWrapper<PingjiagangweiEntity> ew = new EntityWrapper<PingjiagangweiEntity>();


        //查询结果
		PageUtils page = pingjiagangweiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, pingjiagangwei), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,PingjiagangweiEntity pingjiagangwei, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<PingjiagangweiEntity> ew = new EntityWrapper<PingjiagangweiEntity>();

        //查询结果
		PageUtils page = pingjiagangweiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, pingjiagangwei), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( PingjiagangweiEntity pingjiagangwei){
       	EntityWrapper<PingjiagangweiEntity> ew = new EntityWrapper<PingjiagangweiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( pingjiagangwei, "pingjiagangwei")); 
        return R.ok().put("data", pingjiagangweiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(PingjiagangweiEntity pingjiagangwei){
        EntityWrapper< PingjiagangweiEntity> ew = new EntityWrapper< PingjiagangweiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( pingjiagangwei, "pingjiagangwei")); 
		PingjiagangweiView pingjiagangweiView =  pingjiagangweiService.selectView(ew);
		return R.ok("查询评价岗位成功").put("data", pingjiagangweiView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        PingjiagangweiEntity pingjiagangwei = pingjiagangweiService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(pingjiagangwei,deSens);
        return R.ok().put("data", pingjiagangwei);
    }

    /**
     * 前台详情
     */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        PingjiagangweiEntity pingjiagangwei = pingjiagangweiService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(pingjiagangwei,deSens);
        return R.ok().put("data", pingjiagangwei);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增评价岗位") 
    public R save(@RequestBody PingjiagangweiEntity pingjiagangwei, HttpServletRequest request){
        //ValidatorUtils.validateEntity(pingjiagangwei);
        pingjiagangweiService.insert(pingjiagangwei);
        return R.ok().put("data",pingjiagangwei.getId());
    }
    
    /**
     * 前台保存
     */
    @SysLog("新增评价岗位")
    @RequestMapping("/add")
    public R add(@RequestBody PingjiagangweiEntity pingjiagangwei, HttpServletRequest request){
        //ValidatorUtils.validateEntity(pingjiagangwei);
        pingjiagangweiService.insert(pingjiagangwei);
        return R.ok().put("data",pingjiagangwei.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改评价岗位")
    public R update(@RequestBody PingjiagangweiEntity pingjiagangwei, HttpServletRequest request){
        //ValidatorUtils.validateEntity(pingjiagangwei);
        //全部更新
        pingjiagangweiService.updateById(pingjiagangwei);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除评价岗位")
    public R delete(@RequestBody Long[] ids){
        pingjiagangweiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    








}
