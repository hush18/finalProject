package com.team3.admin.map.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.team3.admin.map.dto.MapDto;
import com.team3.user.member.dto.MemberDto;
/**
 * 이름 : 김용기
 * 내용 : 관리자 영업점관련  Dao Class
 */
@Component
public class AdminMapDaoImp implements AdminMapDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public int mapInsert(MapDto mapDto) {
		return sqlSessionTemplate.insert("mapInsert", mapDto);
	}

	@Override
	public List<MapDto> mapRead() {
		List<MapDto> mapList=sqlSessionTemplate.selectList("mapRead");
		for (int i = 0; i < mapList.size(); i++) {
			mapList.get(i).setDirections(mapList.get(i).getDirections().replace("<br>", "\r\n"));
			mapList.get(i).setStore_explanation(mapList.get(i).getStore_explanation().replace("<br>", "\r\n"));
		}
		return mapList;
	}
	
	@Override
	public MemberDto getMemberInfo(Map<String, String> infoMap) {
		return sqlSessionTemplate.selectOne("com.team3.user.member.dao.mapper.memberLogin",infoMap);
	}
	
	@Override
	public int mapDelete(String store_name) {
		return sqlSessionTemplate.delete("mapDelete",store_name);
	}
	
	@Override
	public int mapUpdate(MapDto mapDto) {
		return sqlSessionTemplate.update("mapUpdate",mapDto);
	}
	
	@Override
	public int getMember_number(String id) {
		return sqlSessionTemplate.selectOne("adminCheck",id);
	}
}
