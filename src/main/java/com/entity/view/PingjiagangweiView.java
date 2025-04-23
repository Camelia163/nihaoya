package com.entity.view;

import com.entity.PingjiagangweiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 评价岗位
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2025-04-13 19:13:19
 */
@TableName("pingjiagangwei")
public class PingjiagangweiView  extends PingjiagangweiEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public PingjiagangweiView(){
	}
 
 	public PingjiagangweiView(PingjiagangweiEntity pingjiagangweiEntity){
 	try {
			BeanUtils.copyProperties(this, pingjiagangweiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
