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

import com.entity.GongzifafangEntity;
import com.entity.view.GongzifafangView;

import com.service.GongzifafangService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 工资发放
 * 后端接口
 * @author 
 * @email 
 * @date 2025-04-13 19:13:19
 */
@RestController
@RequestMapping("/gongzifafang")
public class GongzifafangController {
    @Autowired
    private GongzifafangService gongzifafangService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,GongzifafangEntity gongzifafang,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("yonggongdanwei")) {
			gongzifafang.setDanweizhanghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("xuesheng")) {
			gongzifafang.setXueshengzhanghao((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        EntityWrapper<GongzifafangEntity> ew = new EntityWrapper<GongzifafangEntity>();


        //查询结果
		PageUtils page = gongzifafangService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, gongzifafang), params), params));
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
    public R list(@RequestParam Map<String, Object> params,GongzifafangEntity gongzifafang, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<GongzifafangEntity> ew = new EntityWrapper<GongzifafangEntity>();

        //查询结果
		PageUtils page = gongzifafangService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, gongzifafang), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( GongzifafangEntity gongzifafang){
       	EntityWrapper<GongzifafangEntity> ew = new EntityWrapper<GongzifafangEntity>();
      	ew.allEq(MPUtil.allEQMapPre( gongzifafang, "gongzifafang")); 
        return R.ok().put("data", gongzifafangService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(GongzifafangEntity gongzifafang){
        EntityWrapper< GongzifafangEntity> ew = new EntityWrapper< GongzifafangEntity>();
 		ew.allEq(MPUtil.allEQMapPre( gongzifafang, "gongzifafang")); 
		GongzifafangView gongzifafangView =  gongzifafangService.selectView(ew);
		return R.ok("查询工资发放成功").put("data", gongzifafangView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        GongzifafangEntity gongzifafang = gongzifafangService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(gongzifafang,deSens);
        return R.ok().put("data", gongzifafang);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        GongzifafangEntity gongzifafang = gongzifafangService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(gongzifafang,deSens);
        return R.ok().put("data", gongzifafang);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增工资发放") 
    public R save(@RequestBody GongzifafangEntity gongzifafang, HttpServletRequest request){
        //ValidatorUtils.validateEntity(gongzifafang);
        gongzifafangService.insert(gongzifafang);
        return R.ok().put("data",gongzifafang.getId());
    }
    
    /**
     * 前台保存
     */
    @SysLog("新增工资发放")
    @RequestMapping("/add")
    public R add(@RequestBody GongzifafangEntity gongzifafang, HttpServletRequest request){
        //ValidatorUtils.validateEntity(gongzifafang);
        gongzifafangService.insert(gongzifafang);
        return R.ok().put("data",gongzifafang.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改工资发放")
    public R update(@RequestBody GongzifafangEntity gongzifafang, HttpServletRequest request){
        //ValidatorUtils.validateEntity(gongzifafang);
        //全部更新
        gongzifafangService.updateById(gongzifafang);
        return R.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/shBatch")
    @Transactional
    @SysLog("审核工资发放")
    public R update(@RequestBody Long[] ids, @RequestParam String sfsh, @RequestParam String shhf){
        List<GongzifafangEntity> list = new ArrayList<GongzifafangEntity>();
        for(Long id : ids) {
            GongzifafangEntity gongzifafang = gongzifafangService.selectById(id);
            gongzifafang.setSfsh(sfsh);
            gongzifafang.setShhf(shhf);
            list.add(gongzifafang);
        }
        gongzifafangService.updateBatchById(list);
        return R.ok();
    }


    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除工资发放")
    public R delete(@RequestBody Long[] ids){
        gongzifafangService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    








}
