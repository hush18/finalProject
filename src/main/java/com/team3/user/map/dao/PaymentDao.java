package com.team3.user.map.dao;

import java.util.HashMap;
import java.util.List;

import com.team3.user.book.dto.BookDto;
import com.team3.user.map.dto.PaymentPointDto;
import com.team3.user.member.dto.MemberAddressDto;
import com.team3.user.order.dto.OrderDto;

public interface PaymentDao {
	public BookDto selectBook(String isbn);
	public List<MemberAddressDto> getMemberAddress(String id);
	public int insertZipcode(MemberAddressDto memberAddressDto);
	public int deleteMemeberAddress(String member_zipcode);
	public int paymentOk(PaymentPointDto paymentPointDto,OrderDto orderDto,HashMap<String, Object>hmap);
}
