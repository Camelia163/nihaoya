package com.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;
import java.lang.*;
import java.math.*;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import com.entity.TokenEntity;
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

import com.entity.YonggongdanweiEntity;
import com.entity.view.YonggongdanweiView;

import com.service.YonggongdanweiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 用工单位
 * 后端接口
 * @author 
 * @email 
 * @date 2025-04-13 19:13:18
 */
@RestController
@RequestMapping("/yonggongdanwei")
public class YonggongdanweiController {
    @Autowired
    private YonggongdanweiService yonggongdanweiService;






    
	@Autowired
	private TokenService tokenService;
	
	/**
	 * 登录
	 */
	@IgnoreAuth
	@RequestMapping(value = "/login")
	public R login(String username, String password, String captcha, HttpServletRequest request) {
		// 根据登录查询用户信息
        YonggongdanweiEntity u = yonggongdanweiService.selectOne(new EntityWrapper<YonggongdanweiEntity>().eq("danweizhanghao", username));
        // 当用户不存在或验证密码不通过时
		if(u==null || !u.getMima().equals(password)) {
            //账号或密码不正确提示
			return R.error("账号或密码不正确");
		}
		
        // 如果用户是待审核状态，返回请联系管理员审核提示
        if(!"是".equals(u.getSfsh())) return R.error("账号已锁定，请联系管理员审核。");
        // 获取登录token
		String token = tokenService.generateToken(u.getId(), username,"yonggongdanwei",  "管理员" );
        //返回token
		return R.ok().put("token", token);
	}


	
	/**
     * 注册
     */
	@IgnoreAuth
    @RequestMapping("/register")
    public R register(@RequestBody YonggongdanweiEntity yonggongdanwei){
    	//ValidatorUtils.validateEntity(yonggongdanwei);
        //根据登录账号获取用户信息判断是否存在该用户，否则返回错误信息
    	YonggongdanweiEntity u = yonggongdanweiService.selectOne(new EntityWrapper<YonggongdanweiEntity>().eq("danweizhanghao", yonggongdanwei.getDanweizhanghao()));
		if(u!=null) {
			return R.error("注册用户已存在");
		}
        //判断是否存在相同单位账号，否则返回错误信息
        if(yonggongdanweiService.selectCount(new EntityWrapper<YonggongdanweiEntity>().eq("danweizhanghao", yonggongdanwei.getDanweizhanghao()))>0) {
            return R.error("单位账号已存在");
        }
		Long uId = new Date().getTime();
		yonggongdanwei.setId(uId);
        //保存用户
        yonggongdanweiService.insert(yonggongdanwei);
        return R.ok();
    }

	
	/**
	 * 退出
	 */
	@RequestMapping("/logout")
	public R logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return R.ok("退出成功");
	}
	
	/**
     * 获取用户的session用户信息
     */
    @RequestMapping("/session")
    public R getCurrUser(HttpServletRequest request){
    	Long id = (Long)request.getSession().getAttribute("userId");
        YonggongdanweiEntity u = yonggongdanweiService.selectById(id);
        return R.ok().put("data", u);
    }
    
    /**
     * 密码重置
     */
    @IgnoreAuth
	@RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request){
    	//根据登录账号判断是否存在用户信息，否则返回错误信息
        YonggongdanweiEntity u = yonggongdanweiService.selectOne(new EntityWrapper<YonggongdanweiEntity>().eq("danweizhanghao", username));
    	if(u==null) {
    		return R.error("账号不存在");
    	}
        //重置密码为123456
        u.setMima("123456");
        yonggongdanweiService.updateById(u);
        return R.ok("密码已重置为：123456");
    }



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,YonggongdanweiEntity yonggongdanwei,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<YonggongdanweiEntity> ew = new EntityWrapper<YonggongdanweiEntity>();


        //查询结果
		PageUtils page = yonggongdanweiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yonggongdanwei), params), params));
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
    public R list(@RequestParam Map<String, Object> params,YonggongdanweiEntity yonggongdanwei, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<YonggongdanweiEntity> ew = new EntityWrapper<YonggongdanweiEntity>();

        //查询结果
		PageUtils page = yonggongdanweiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yonggongdanwei), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( YonggongdanweiEntity yonggongdanwei){
       	EntityWrapper<YonggongdanweiEntity> ew = new EntityWrapper<YonggongdanweiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( yonggongdanwei, "yonggongdanwei")); 
        return R.ok().put("data", yonggongdanweiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(YonggongdanweiEntity yonggongdanwei){
        EntityWrapper< YonggongdanweiEntity> ew = new EntityWrapper< YonggongdanweiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( yonggongdanwei, "yonggongdanwei")); 
		YonggongdanweiView yonggongdanweiView =  yonggongdanweiService.selectView(ew);
		return R.ok("查询用工单位成功").put("data", yonggongdanweiView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        YonggongdanweiEntity yonggongdanwei = yonggongdanweiService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(yonggongdanwei,deSens);
        return R.ok().put("data", yonggongdanwei);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        YonggongdanweiEntity yonggongdanwei = yonggongdanweiService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(yonggongdanwei,deSens);
        return R.ok().put("data", yonggongdanwei);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增用工单位") 
    public R save(@RequestBody YonggongdanweiEntity yonggongdanwei, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(yonggongdanweiService.selectCount(new EntityWrapper<YonggongdanweiEntity>().eq("danweizhanghao", yonggongdanwei.getDanweizhanghao()))>0) {
            return R.error("单位账号已存在");
        }
        //ValidatorUtils.validateEntity(yonggongdanwei);
        //验证账号唯一性，否则返回错误信息
        YonggongdanweiEntity u = yonggongdanweiService.selectOne(new EntityWrapper<YonggongdanweiEntity>().eq("danweizhanghao", yonggongdanwei.getDanweizhanghao()));
        if(u!=null) {
            return R.error("用户已存在");
        }
    	yonggongdanwei.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
		yonggongdanwei.setId(new Date().getTime());
        yonggongdanweiService.insert(yonggongdanwei);
        return R.ok().put("data",yonggongdanwei.getId());
    }
    
    /**
     * 前台保存
     */
    @SysLog("新增用工单位")
    @RequestMapping("/add")
    public R add(@RequestBody YonggongdanweiEntity yonggongdanwei, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(yonggongdanweiService.selectCount(new EntityWrapper<YonggongdanweiEntity>().eq("danweizhanghao", yonggongdanwei.getDanweizhanghao()))>0) {
            return R.error("单位账号已存在");
        }
        //ValidatorUtils.validateEntity(yonggongdanwei);
        //验证账号唯一性，否则返回错误信息
        YonggongdanweiEntity u = yonggongdanweiService.selectOne(new EntityWrapper<YonggongdanweiEntity>().eq("danweizhanghao", yonggongdanwei.getDanweizhanghao()));
        if(u!=null) {
            return R.error("用户已存在");
        }
    	yonggongdanwei.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
		yonggongdanwei.setId(new Date().getTime());
        yonggongdanweiService.insert(yonggongdanwei);
        return R.ok().put("data",yonggongdanwei.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改用工单位")
    public R update(@RequestBody YonggongdanweiEntity yonggongdanwei, HttpServletRequest request){
        //ValidatorUtils.validateEntity(yonggongdanwei);
        //验证字段唯一性，否则返回错误信息
        if(yonggongdanweiService.selectCount(new EntityWrapper<YonggongdanweiEntity>().ne("id", yonggongdanwei.getId()).eq("danweizhanghao", yonggongdanwei.getDanweizhanghao()))>0) {
            return R.error("单位账号已存在");
        }
        //全部更新
        yonggongdanweiService.updateById(yonggongdanwei);
        if(null!=yonggongdanwei.getDanweizhanghao())
        {
            // 修改token
            TokenEntity tokenEntity = new TokenEntity();
            tokenEntity.setUsername(yonggongdanwei.getDanweizhanghao());
            tokenService.update(tokenEntity, new EntityWrapper<TokenEntity>().eq("userid", yonggongdanwei.getId()));
        }
        return R.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/shBatch")
    @Transactional
    @SysLog("审核用工单位")
    public R update(@RequestBody Long[] ids, @RequestParam String sfsh, @RequestParam String shhf){
        List<YonggongdanweiEntity> list = new ArrayList<YonggongdanweiEntity>();
        for(Long id : ids) {
            YonggongdanweiEntity yonggongdanwei = yonggongdanweiService.selectById(id);
            yonggongdanwei.setSfsh(sfsh);
            yonggongdanwei.setShhf(shhf);
            list.add(yonggongdanwei);
        }
        yonggongdanweiService.updateBatchById(list);
        return R.ok();
    }


    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除用工单位")
    public R delete(@RequestBody Long[] ids){
        yonggongdanweiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    








}
