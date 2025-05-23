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

import com.entity.MessagesEntity;
import com.entity.view.MessagesView;

import com.service.MessagesService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 意见反馈
 * 后端接口
 * @author 
 * @email 
 * @date 2025-04-13 19:13:20
 */
@RestController
@RequestMapping("/messages")
public class MessagesController {
    @Autowired
    private MessagesService messagesService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,MessagesEntity messages,
		HttpServletRequest request){
        if(!request.getSession().getAttribute("role").toString().equals("管理员")) {
            messages.setUserid((Long)request.getSession().getAttribute("userId"));
        }
        //设置查询条件
        EntityWrapper<MessagesEntity> ew = new EntityWrapper<MessagesEntity>();


        //查询结果
		PageUtils page = messagesService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, messages), params), params));
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
    public R list(@RequestParam Map<String, Object> params,MessagesEntity messages, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<MessagesEntity> ew = new EntityWrapper<MessagesEntity>();

        //查询结果
		PageUtils page = messagesService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, messages), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( MessagesEntity messages){
       	EntityWrapper<MessagesEntity> ew = new EntityWrapper<MessagesEntity>();
      	ew.allEq(MPUtil.allEQMapPre( messages, "messages")); 
        return R.ok().put("data", messagesService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(MessagesEntity messages){
        EntityWrapper< MessagesEntity> ew = new EntityWrapper< MessagesEntity>();
 		ew.allEq(MPUtil.allEQMapPre( messages, "messages")); 
		MessagesView messagesView =  messagesService.selectView(ew);
		return R.ok("查询意见反馈成功").put("data", messagesView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        MessagesEntity messages = messagesService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(messages,deSens);
        return R.ok().put("data", messages);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        MessagesEntity messages = messagesService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(messages,deSens);
        return R.ok().put("data", messages);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增意见反馈") 
    public R save(@RequestBody MessagesEntity messages, HttpServletRequest request){
        //ValidatorUtils.validateEntity(messages);
        messagesService.insert(messages);
        return R.ok().put("data",messages.getId());
    }
    
    /**
     * 前台保存
     */
    @SysLog("新增意见反馈")
    @RequestMapping("/add")
    public R add(@RequestBody MessagesEntity messages, HttpServletRequest request){
        //ValidatorUtils.validateEntity(messages);
        messagesService.insert(messages);
        return R.ok().put("data",messages.getId());
    }



     /**
     * 获取用户密保
     */
    @RequestMapping("/security")
    @IgnoreAuth
    public R security(@RequestParam String username){
        MessagesEntity messages = messagesService.selectOne(new EntityWrapper<MessagesEntity>().eq("", username));
        return R.ok().put("data", messages);
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @IgnoreAuth
    public R update(@RequestBody MessagesEntity messages, HttpServletRequest request){
        //ValidatorUtils.validateEntity(messages);
        //全部更新
        messagesService.updateById(messages);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除意见反馈")
    public R delete(@RequestBody Long[] ids){
        messagesService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	/**
     * 前台智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,MessagesEntity messages, HttpServletRequest request,String pre){
        EntityWrapper<MessagesEntity> ew = new EntityWrapper<MessagesEntity>();
        Map<String, Object> newMap = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
        // 组装参数
		Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			String newKey = entry.getKey();
			if (pre.endsWith(".")) {
				newMap.put(pre + newKey, entry.getValue());
			} else if (StringUtils.isEmpty(pre)) {
				newMap.put(newKey, entry.getValue());
			} else {
				newMap.put(pre + "." + newKey, entry.getValue());
			}
		}
		params.put("sort", "clicktime");
        params.put("order", "desc");

		PageUtils page = messagesService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, messages), params), params));
        return R.ok().put("data", page);
    }








}
