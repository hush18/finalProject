package com.team3.user.map.dao;

import java.util.List;

import com.team3.user.book.dto.BookDto;
import com.team3.user.member.dto.MemberAddressDto;

public interface PaymentDao {
	public BookDto selectBook(String isbn);
	public List<MemberAddressDto> getMemberAddress(String id);
	public int insertZipcode(MemberAddressDto memberAddressDto);
	public int deleteMemeberAddress(String member_zipcode);
}
