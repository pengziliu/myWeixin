package com.lzp.util;

import com.lzp.exception.NullParamException;

/**
 * 参数检查工具类，检查到参数异常抛出对应异常类
 * @author mac
 *
 */
public class ParamUtils {
	
	public static void notNull(Object object) {
		if(object==null){
			try {
				throw new NullParamException();
			} catch (NullParamException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	

}
