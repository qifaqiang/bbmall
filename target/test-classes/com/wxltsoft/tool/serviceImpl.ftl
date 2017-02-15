package ${PACKAGE_URL};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import ${PACKAGE1}
import ${PACKAGE2}
import ${PACKAGE3}

@Service("${CLASS?uncap_first}Service")
public class ${CLASS}ServiceImpl implements ${CLASS}Service {
	
	@Autowired
	private ${CLASS}Mapper ${CLASS?uncap_first}Mapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return ${CLASS?uncap_first}Mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(${CLASS} record) {
		return ${CLASS?uncap_first}Mapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(${CLASS} record) {
		return ${CLASS?uncap_first}Mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public ${CLASS} selectByPrimaryKey(Integer id) {
		return ${CLASS?uncap_first}Mapper.selectByPrimaryKey(id);
	}

	@Override
	public ${CLASS} selectBy${CLASS}(${CLASS} record) {
		return ${CLASS?uncap_first}Mapper.selectBy${CLASS}(record);
	}

	@Override
	public List<${CLASS}> listPageBy${CLASS}(${CLASS} clssname) {
		return ${CLASS?uncap_first}Mapper.listPageBy${CLASS}(clssname);
	}

	@Override
	public List<${CLASS}> getAllBy${CLASS}(${CLASS} clssname) {
		return ${CLASS?uncap_first}Mapper.getAllBy${CLASS}(clssname);
	}
}