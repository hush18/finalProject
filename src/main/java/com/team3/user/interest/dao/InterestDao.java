package com.team3.user.interest.dao;

import java.util.List;

import com.team3.user.interest.dto.InterestDto;

public interface InterestDao {
	public List<InterestDto> nearestSelect(String id);
	public int nearestUp(String id, String[] strArr);
	public int nearestDel(String id, String[] strArr);
	public List<InterestDto> wishListSelect(String id);
	public int wishListUp(String id, String[] strArr);
	public int wishListDel(String id, String[] strArr);
}
