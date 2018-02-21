package com.team3.user.interest.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.team3.aop.LogAspect;
import com.team3.user.interest.dto.InterestDto;

@Component
public class InterestDaoImp implements InterestDao {
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<InterestDto> nearestSelect(String id) {
		List<InterestDto> interestList=sqlSession.selectList("nearestList", id);
		
		return interestList;
	}

	@Override
	public int nearestUp(String id, String[] strArr) {
		int chk=0;
		int check=0;
		for(int i=0;i<strArr.length; i++) {
			Map<String, Object> hMap=new HashMap<String, Object>();
			hMap.put("id", id);
			hMap.put("str", strArr[i]);
			LogAspect.logger.info(LogAspect.logMsg + hMap.toString() + strArr.length + "이건출력됨");
			chk=sqlSession.update("nearestUp",hMap);
			LogAspect.logger.info(LogAspect.logMsg + "chk값 : " + chk);
			if(chk==0) {
				check=0;
			}else {
				check=1;
			}
		}
		LogAspect.logger.info(LogAspect.logMsg + "check값이 왜 0임" +check);
		return check;
	}
	
	@Override
	public int nearestDel(String id, String[] strArr) {
		int chk=0;
		int check=0;
		for(int i=0;i<strArr.length; i++) {
			Map<String, Object> hMap=new HashMap<String, Object>();
			hMap.put("id", id);
			hMap.put("str", strArr[i]);
			LogAspect.logger.info(LogAspect.logMsg + hMap.toString() + strArr.length + "이건출력됨");
			chk=sqlSession.delete("nearestDel",hMap);
			if(chk==0) {
				check=0;
			}else {
				check=1;
			}
		}
		return check;
	}

	@Override
	public List<InterestDto> wishListSelect(String id) {
		List<InterestDto> interestList=sqlSession.selectList("wishList", id);
		
		return interestList;
	}
	
	@Override
	public int wishListUp(String id, String[] strArr) {
		int chk=0;
		int check=0;
		for(int i=0;i<strArr.length; i++) {
			Map<String, Object> hMap=new HashMap<String, Object>();
			hMap.put("id", id);
			hMap.put("str", strArr[i]);
			LogAspect.logger.info(LogAspect.logMsg + hMap.toString() + strArr.length + "이건출력됨");
			chk=sqlSession.update("nearestUp",hMap);
			LogAspect.logger.info(LogAspect.logMsg + "chk값 : " + chk);
			if(chk==0) {
				check=0;
			}else {
				check=1;
			}
		}
		LogAspect.logger.info(LogAspect.logMsg + "check값이 왜 0임" +check);
		return check;
	}
	
	@Override
	public int wishListDel(String id, String[] strArr) {
		int chk=0;
		int check=0;
		for(int i=0;i<strArr.length; i++) {
			Map<String, Object> hMap=new HashMap<String, Object>();
			hMap.put("id", id);
			hMap.put("str", strArr[i]);
			LogAspect.logger.info(LogAspect.logMsg + hMap.toString() + strArr.length + "이건출력됨");
			chk=sqlSession.delete("nearestDel",hMap);
			if(chk==0) {
				check=0;
			}else {
				check=1;
			}
		}
		return check;
	}

	@Override
	public int wishListInsert(String id, String[] strArr) {
		int chk=0;
		int check=0;
		for(int i=0;i<strArr.length; i++) {
			Map<String, Object> hMap=new HashMap<String, Object>();
			hMap.put("id", id);
			hMap.put("isbn", strArr[i]);
			LogAspect.logger.info(LogAspect.logMsg + hMap.toString());
			chk=sqlSession.delete("nearestDel",hMap);
			if(chk==0) {
				check=0;
			}else {
				check=1;
			}
		}
		return check;
	}
	@Override
	public int nearestInsert(String id, String isbn) {
		Map<String, Object> hMap=new HashMap<String, Object>();
		hMap.put("id", id);
		hMap.put("isbn", isbn);
		LogAspect.logger.info(LogAspect.logMsg + hMap.toString());
		return sqlSession.insert("nearestInsert",hMap);
	}

	@Override
	public List<InterestDto> scrollSelect(String id) {
		LogAspect.logger.info(LogAspect.logMsg + "여기까지는?????" + id);
		return sqlSession.selectList("scrollSelect", id);
	}
}
