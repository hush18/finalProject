package com.team3.admin.map.dao;

import java.util.List;
import java.util.Map;

import com.team3.admin.map.dto.MapDto;
import com.team3.user.member.dto.MemberDto;

public interface AdminMapDao {
	public int mapInsert(MapDto mapDto);
	public List<MapDto> mapRead();
	public MemberDto getMemberInfo(Map<String, String> infoMap);
	public int mapDelete(String store_name);
	public int mapUpdate(MapDto mapDto);
	
}
