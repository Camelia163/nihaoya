package com.entity.view;

import com.entity.PingjiaxueshengEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 评价学生
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2025-04-13 19:13:19
 */
@TableName("pingjiaxuesheng")
public class PingjiaxueshengView  extends PingjiaxueshengEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public PingjiaxueshengView(){
	}
 
 	public PingjiaxueshengView(PingjiaxueshengEntity pingjiaxueshengEntity){
 	try {
			BeanUtils.copyProperties(this, pingjiaxueshengEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
