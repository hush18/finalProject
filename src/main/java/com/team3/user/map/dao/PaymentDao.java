package com.team3.user.map.dao;

import java.util.HashMap;
import java.util.List;

import com.team3.user.book.dto.BookDto;
import com.team3.user.map.dto.PaymentPointDto;
import com.team3.user.map.dto.PointDto;
import com.team3.user.member.dto.MemberAddressDto;
import com.team3.user.order.dto.OrderDto;

/**
 * 이름 : 김용기
 * 날짜 : 2018. 2. 28.
 * 시간 : 오후 10:24:28
 * 내용 : 결제  Dao Interface
 */
public interface PaymentDao {
	public BookDto selectBook(String isbn);
	public List<MemberAddressDto> getMemberAddress(String id);
	public int insertZipcode(MemberAddressDto memberAddressDto);
	public int deleteMemeberAddress(String member_zipcode);
	public int paymentOk(PaymentPointDto paymentPointDto,OrderDto orderDto,HashMap<String, Object>hmap,HashMap<String, Object>cartMap);
	public List<PointDto> selectPoint(String id);
	public HashMap<String, Object>selectState(String id);
}
