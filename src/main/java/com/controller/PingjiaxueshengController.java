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

import com.entity.PingjiaxueshengEntity;
import com.entity.view.PingjiaxueshengView;

import com.service.PingjiaxueshengService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 评价学生
 * 后端接口
 * @author 
 * @email 
 * @date 2025-04-13 19:13:19
 */
@RestController
@RequestMapping("/pingjiaxuesheng")
public class PingjiaxueshengController {
    @Autowired
    private PingjiaxueshengService pingjiaxueshengService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,PingjiaxueshengEntity pingjiaxuesheng,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("xuesheng")) {
			pingjiaxuesheng.setXueshengzhanghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("yonggongdanwei")) {
			pingjiaxuesheng.setDanweizhanghao((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        EntityWrapper<PingjiaxueshengEntity> ew = new EntityWrapper<PingjiaxueshengEntity>();


        //查询结果
		PageUtils page = pingjiaxueshengService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, pingjiaxuesheng), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,PingjiaxueshengEntity pingjiaxuesheng, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<PingjiaxueshengEntity> ew = new EntityWrapper<PingjiaxueshengEntity>();

        //查询结果
		PageUtils page = pingjiaxueshengService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, pingjiaxuesheng), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( PingjiaxueshengEntity pingjiaxuesheng){
       	EntityWrapper<PingjiaxueshengEntity> ew = new EntityWrapper<PingjiaxueshengEntity>();
      	ew.allEq(MPUtil.allEQMapPre( pingjiaxuesheng, "pingjiaxuesheng")); 
        return R.ok().put("data", pingjiaxueshengService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(PingjiaxueshengEntity pingjiaxuesheng){
        EntityWrapper< PingjiaxueshengEntity> ew = new EntityWrapper< PingjiaxueshengEntity>();
 		ew.allEq(MPUtil.allEQMapPre( pingjiaxuesheng, "pingjiaxuesheng")); 
		PingjiaxueshengView pingjiaxueshengView =  pingjiaxueshengService.selectView(ew);
		return R.ok("查询评价学生成功").put("data", pingjiaxueshengView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        PingjiaxueshengEntity pingjiaxuesheng = pingjiaxueshengService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(pingjiaxuesheng,deSens);
        return R.ok().put("data", pingjiaxuesheng);
    }

    /**
     * 前台详情
     */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        PingjiaxueshengEntity pingjiaxuesheng = pingjiaxueshengService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(pingjiaxuesheng,deSens);
        return R.ok().put("data", pingjiaxuesheng);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增评价学生") 
    public R save(@RequestBody PingjiaxueshengEntity pingjiaxuesheng, HttpServletRequest request){
        //ValidatorUtils.validateEntity(pingjiaxuesheng);
        pingjiaxueshengService.insert(pingjiaxuesheng);
        return R.ok().put("data",pingjiaxuesheng.getId());
    }
    
    /**
     * 前台保存
     */
    @SysLog("新增评价学生")
    @RequestMapping("/add")
    public R add(@RequestBody PingjiaxueshengEntity pingjiaxuesheng, HttpServletRequest request){
        //ValidatorUtils.validateEntity(pingjiaxuesheng);
        pingjiaxueshengService.insert(pingjiaxuesheng);
        return R.ok().put("data",pingjiaxuesheng.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改评价学生")
    public R update(@RequestBody PingjiaxueshengEntity pingjiaxuesheng, HttpServletRequest request){
        //ValidatorUtils.validateEntity(pingjiaxuesheng);
        //全部更新
        pingjiaxueshengService.updateById(pingjiaxuesheng);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除评价学生")
    public R delete(@RequestBody Long[] ids){
        pingjiaxueshengService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    








}
