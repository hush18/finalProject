package com.team3.user.map.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.team3.user.book.dto.BookDto;
import com.team3.user.map.dto.PaymentPointDto;
import com.team3.user.map.dto.PointDto;
import com.team3.user.member.dto.MemberAddressDto;
import com.team3.user.order.dto.OrderDto;

/**
 * 이름 : 김용기
 * 날짜 : 2018. 2. 28.
 * 시간 : 오후 10:25:08
 * 내용 : 결제 Dao Class 
 */
@Component
public class PaymentDaoImp implements PaymentDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public BookDto selectBook(String isbn) {
		return sqlSessionTemplate.selectOne("selectBookPrice", isbn);
	}

	@Override
	public List<MemberAddressDto> getMemberAddress(String id) {
		return sqlSessionTemplate.selectList("member_address_list", id);
	}

	@Override
	public int insertZipcode(MemberAddressDto memberAddressDto) {
		return sqlSessionTemplate.insert("zipcodeInsert", memberAddressDto);
	}

	@Override
	public int deleteMemeberAddress(String member_zipcode) {
		return sqlSessionTemplate.delete("zipcodeDelete", member_zipcode);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public int paymentOk(PaymentPointDto paymentPointDto, OrderDto orderDto, HashMap<String, Object>map, HashMap<String, Object>cartMap) {
		int check=sqlSessionTemplate.insert("paymentOk_paypoint",paymentPointDto);
		check=sqlSessionTemplate.insert("paymentOk_order",orderDto);
		check=sqlSessionTemplate.insert("com.team3.user.map.dao.mapper.insert_sales",map);
		check=sqlSessionTemplate.update("com.team3.user.map.dao.mapper.update_member",map);
		
		if(cartMap.get("isbnList")!=null) {
			sqlSessionTemplate.delete("pay_delete_cart",cartMap);
		}
		
		return check;
	}
	
	@Override
	public List<PointDto> selectPoint(String id) {
		return sqlSessionTemplate.selectList("selectPoint",id);
	}
	@Override
	public HashMap<String, Object> selectState(String id) {
		HashMap<String, Object>selectState=new HashMap<String, Object>();
		int orderingCount=sqlSessionTemplate.selectOne("com.team3.user.map.dao.mapper.orderingCount",id);
		int deliveryCount=sqlSessionTemplate.selectOne("com.team3.user.map.dao.mapper.deliveryCount",id);
		ArrayList<Integer>list=new ArrayList<Integer>(); 
		for(int i=11;i<=32;i++) {
			list.add(i);
		}
		selectState.put("list",list);
		selectState.put("id",id);
		
		int cancelCount=sqlSessionTemplate.selectOne("com.team3.user.map.dao.mapper.cancelCount",selectState);
		
		selectState.put("orderingCount", orderingCount);
		selectState.put("deliveryCount", deliveryCount);
		selectState.put("cancelCount", cancelCount);
		
		return selectState;
	}
	
}

