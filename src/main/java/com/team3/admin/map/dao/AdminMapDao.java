package com.team3.admin.map.dao;

import java.util.HashMap;
import java.util.List;

import com.team3.admin.map.dto.MapDto;

public interface AdminMapDao {
	public int mapInsert(MapDto mapDto);
	public List<MapDto> mapRead();
	public int mapDelete(HashMap<String, String> stringMap);
}
