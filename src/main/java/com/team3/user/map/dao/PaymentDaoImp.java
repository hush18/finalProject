package com.team3.user.map.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.team3.user.book.dto.BookDto;
import com.team3.user.member.dto.MemberAddressDto;

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
}
