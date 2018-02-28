package com.team3.user.cstList.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.team3.aop.LogAspect;
import com.team3.user.cstList.dto.CstListDto;

@Component
public class CstListDaoImp implements CstListDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public int cstListCount(String id) {
		LogAspect.logger.info(LogAspect.logMsg + "id: " +id);
		return sqlSession.selectOne("cstListCount",id);
	}

	@Override
	public List<CstListDto> cstList(String id, int startNum,int endNum) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		
		return sqlSession.selectList("userCstList",map);
	}

}
