package com.team3.user.faq.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.team3.user.faq.dto.FaqDto;

@Component
public class FaqDaoImp implements FaqDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<FaqDto> getTopTenList() {
		return sqlSession.selectList("getTopTenList");
	}

	@Override
	public List<FaqDto> faqList(String upCategory,int startNum,int endNum) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("upCategory", upCategory);
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		return sqlSession.selectList("com.team3.user.faq.dao.mapper.faqList",map);
	}

	@Override
	public List<FaqDto> faqDownList(String downCategory,int startNum,int endNum) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("downCategory", downCategory);
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		return sqlSession.selectList("com.team3.user.faq.dao.mapper.faqDownList",map);
	}

	@Override
	public int faqListCount(String upCategory, String downCategory) {
		System.out.println(upCategory+downCategory);
		if(downCategory==null) {
			return sqlSession.selectOne("upCount",upCategory);
		}else{
			return sqlSession.selectOne("downCount",downCategory);
		}
	}

}
