package com.team3.admin.map.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.team3.admin.map.dto.MapDto;

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
	public int mapDelete(HashMap<String, String> stringMap) {
		return sqlSessionTemplate.delete("mapDelete",stringMap);
	}
}
