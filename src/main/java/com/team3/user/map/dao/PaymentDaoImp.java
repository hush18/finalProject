package com.team3.user.map.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.team3.aop.LogAspect;
import com.team3.user.book.dto.BookDto;
import com.team3.user.map.dto.PaymentPointDto;
import com.team3.user.member.dto.MemberAddressDto;
import com.team3.user.order.dto.OrderDto;

@Component
public class PaymentDaoImp implements PaymentDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public BookDto selectBook(String isbn) {
		return sqlSessionTemplate.selectOne("selectBookPrice",isbn);
	}
	@Override
	public List<MemberAddressDto> getMemberAddress(String id) {
		return sqlSessionTemplate.selectList("member_address_list",id);
	}
	
	@Override
	public int insertZipcode(MemberAddressDto memberAddressDto) {
		return sqlSessionTemplate.insert("zipcodeInsert",memberAddressDto);
	}
	
	@Override
	public int deleteMemeberAddress(String member_zipcode) {
		return sqlSessionTemplate.delete("zipcodeDelete",member_zipcode);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public int paymentOk(PaymentPointDto paymentPointDto, OrderDto orderDto,HashMap<String, Object>map) {
		int check=sqlSessionTemplate.insert("paymentOk_paypoint",paymentPointDto);
		if(check>0) {
			check=sqlSessionTemplate.insert("paymentOk_order",orderDto);
			if (check>0) {
				check=sqlSessionTemplate.insert("com.team3.user.map.dao.mapper.insert_sales",map);
				if(check>0) {
					check=sqlSessionTemplate.update("com.team3.user.map.dao.mapper.update_member",map);
							
				}
			}
		}else {
			check=0;
		}
		return check;
	}
}
