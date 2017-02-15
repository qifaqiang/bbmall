<?xml version="1.0" encoding="UTF-8" ?>  <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${namespace}Mapper">
	<resultMap type="${className}" id="${className?cap_first}Map">
		<id property="${PRIMARY}" column="${PK_colum}" />
		<#list properties as prop>  
		<result property="${prop.property}" column="${prop.column}" />
 		</#list>  
	</resultMap>
	<select id="selectByPrimaryKey" parameterType="Integer"
		resultMap="${className?cap_first}Map"><![CDATA[  SELECT ${selectALl} FROM ${table} tmp WHERE tmp.${PK_colum} = ${r'#{id}'} ]]>
	</select>
	<select id="selectBy${className}" parameterType="${className}" resultMap="${className?cap_first}Map">
		SELECT ${selectALl} FROM ${table} tmp
		<where>
			<#list properties as prop>  
			<if test="${prop.property} != null"> AND ${prop.column} = ${r'#'}{${prop.property}} </if>
	 		</#list>
 		</where>
	</select>
	<select id="listPageBy${className}" parameterType="${className}"
		resultMap="${className?cap_first}Map">
		SELECT ${selectALl} from ${table} tmp
		<where>
			<#list properties as prop>  
			<if test="${prop.property} != null"> AND ${prop.column} = ${r'#'}{${prop.property}} </if>
	 		</#list>
 		</where>
 		 ORDER BY ${PK_colum} DESC
	</select>
	<select id="getAllBy${className}" parameterType="${className}" resultMap="${className?cap_first}Map">
		SELECT ${selectALl} from ${table} tmp
		<where>
			<#list properties as prop>  
			<if test="${prop.property} != null"> AND ${prop.column} = ${r'#'}{${prop.property}} </if>
	 		</#list>
 		</where>
 	    ORDER BY ${PK_colum} DESC
	</select>
	<insert id="insertSelective" parameterType="${className}">
		INSERT INTO ${table}
		<trim prefix="(" suffix=")" suffixOverrides=",">
			<#list properties as prop>  
			<if test="${prop.property} != null">${prop.column},</if>
	 		</#list>
		</trim>
		values
		<trim prefix="(" suffix=")" suffixOverrides=",">
			<#list properties as prop>  
			<if test="${prop.property} != null">${r'#'}{${prop.property}},</if>
	 		</#list>
		</trim>
	</insert>
	<update id="updateByPrimaryKeySelective" parameterType="${className}">
		update ${table}
		<set>
			<#list properties as prop>  
			<if test="${prop.property} != null">${prop.column}=${r'#'}{${prop.property}},</if>
	 		</#list>
		</set>
		where ${PK_colum} = ${r'#{id}'}
	</update>
	<delete id="deleteByPrimaryKey" parameterType="${className}"><![CDATA[delete from ${table} where ${PK_colum}=${r'#{id}'}
]]></delete>
</mapper>