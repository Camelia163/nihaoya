package com.entity.view;

import com.entity.GangweishenqingEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 岗位申请
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2025-04-13 19:13:19
 */
@TableName("gangweishenqing")
public class GangweishenqingView  extends GangweishenqingEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public GangweishenqingView(){
	}
 
 	public GangweishenqingView(GangweishenqingEntity gangweishenqingEntity){
 	try {
			BeanUtils.copyProperties(this, gangweishenqingEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
