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

import com.entity.KaoqindakaEntity;
import com.entity.view.KaoqindakaView;

import com.service.KaoqindakaService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 考勤打卡
 * 后端接口
 * @author 
 * @email 
 * @date 2025-04-13 19:13:19
 */
@RestController
@RequestMapping("/kaoqindaka")
public class KaoqindakaController {
    @Autowired
    private KaoqindakaService kaoqindakaService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,KaoqindakaEntity kaoqindaka,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("xuesheng")) {
			kaoqindaka.setXueshengzhanghao((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        EntityWrapper<KaoqindakaEntity> ew = new EntityWrapper<KaoqindakaEntity>();


        //查询结果
		PageUtils page = kaoqindakaService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, kaoqindaka), params), params));
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
    public R list(@RequestParam Map<String, Object> params,KaoqindakaEntity kaoqindaka, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<KaoqindakaEntity> ew = new EntityWrapper<KaoqindakaEntity>();

        //查询结果
		PageUtils page = kaoqindakaService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, kaoqindaka), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( KaoqindakaEntity kaoqindaka){
       	EntityWrapper<KaoqindakaEntity> ew = new EntityWrapper<KaoqindakaEntity>();
      	ew.allEq(MPUtil.allEQMapPre( kaoqindaka, "kaoqindaka")); 
        return R.ok().put("data", kaoqindakaService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(KaoqindakaEntity kaoqindaka){
        EntityWrapper< KaoqindakaEntity> ew = new EntityWrapper< KaoqindakaEntity>();
 		ew.allEq(MPUtil.allEQMapPre( kaoqindaka, "kaoqindaka")); 
		KaoqindakaView kaoqindakaView =  kaoqindakaService.selectView(ew);
		return R.ok("查询考勤打卡成功").put("data", kaoqindakaView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        KaoqindakaEntity kaoqindaka = kaoqindakaService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(kaoqindaka,deSens);
        return R.ok().put("data", kaoqindaka);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        KaoqindakaEntity kaoqindaka = kaoqindakaService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(kaoqindaka,deSens);
        return R.ok().put("data", kaoqindaka);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增考勤打卡") 
    public R save(@RequestBody KaoqindakaEntity kaoqindaka, HttpServletRequest request){
        //ValidatorUtils.validateEntity(kaoqindaka);
        kaoqindakaService.insert(kaoqindaka);
        return R.ok().put("data",kaoqindaka.getId());
    }
    
    /**
     * 前台保存
     */
    @SysLog("新增考勤打卡")
    @RequestMapping("/add")
    public R add(@RequestBody KaoqindakaEntity kaoqindaka, HttpServletRequest request){
        //ValidatorUtils.validateEntity(kaoqindaka);
        kaoqindakaService.insert(kaoqindaka);
        return R.ok().put("data",kaoqindaka.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改考勤打卡")
    public R update(@RequestBody KaoqindakaEntity kaoqindaka, HttpServletRequest request){
        //ValidatorUtils.validateEntity(kaoqindaka);
        //全部更新
        kaoqindakaService.updateById(kaoqindaka);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除考勤打卡")
    public R delete(@RequestBody Long[] ids){
        kaoqindakaService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    








}
