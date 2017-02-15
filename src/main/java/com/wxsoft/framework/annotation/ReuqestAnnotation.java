/**   
 * @文件名称: ReuqestAnnotation.java
 * @类路径: com.wxsoft.framework.plugin
 * @描述: 
 * @作者：kasiaits
 * @时间：2013-3-12 下午01:11:21  
 */

package com.wxsoft.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 
 * @类功能说明：request注解声明
 * @类修改者：kasiait
 * @修改日期：2013-4-7
 * @修改说明：
 * @公司名称：kyz
 * @作者：kasiaits
 * @创建时间：2013-4-7 上午10:41:23
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ReuqestAnnotation {

	String name() default "";             //传入的参数名
	boolean dateString() default false;   //是否为dateString类型
}
