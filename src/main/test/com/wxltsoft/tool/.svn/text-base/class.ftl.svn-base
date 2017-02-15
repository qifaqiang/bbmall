package ${PACKAGE_URL};

${import_URL}

/**
 * @文件名称: ${CLASS}.java
 * @类路径: ${PACKAGE}
 * @描述: TODO
 * @作者：${AUTH_NAME}
 * @公司：${COMPANY_NAME}
 * @时间：${TIME}
 */
public class ${CLASS} extends BaseBean {
	private static final long serialVersionUID = 1L;
	
	<#list properties as prop>  
	private ${prop.type} ${prop.name};// ${prop.comment} 
 	</#list>  
  
    <#list properties as prop>  
	public ${prop.type} get${prop.name?cap_first}(){  
		return ${prop.name};  
	}  
	public void set${prop.name?cap_first}(${prop.type} ${prop.name}){  
		this.${prop.name} = ${prop.name};  
	}  
    </#list>  

}