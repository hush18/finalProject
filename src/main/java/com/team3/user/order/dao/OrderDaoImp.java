package com.team3.user.order.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.team3.aop.LogAspect;
import com.team3.user.order.dto.CartDto;
import com.team3.user.order.dto.OrderDto;

@Component
public class OrderDaoImp implements OrderDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public int getOrderSearchCount() {
		String id="tlsghdyd93";
		return sqlSession.selectOne("orderSearchCount", id);
	}
	
	@Override
	public int getOrderingCount() {
		String id="tlsghdyd93";
		return sqlSession.selectOne("orderingCount", id);
	}

	@Override
	public int getDeliveryCount() {
		String id="tlsghdyd93";
		return sqlSession.selectOne("deliveryCount", id);
	}

	@Override
	public int getCancelCount() {
		String id="tlsghdyd93";
		return sqlSession.selectOne("cancelCount", id);
	}

	@Override
	public int getPoint() {
		String id="tlsghdyd93";
		return sqlSession.selectOne("point", id);
	}
	
	@Override
	public List<OrderDto> orderSearchList(int startRow, int endRow, int list_id) {
		Map<String, Object> map=new HashMap<String, Object>();
		List<OrderDto> str=null;
		if(list_id==0) {
			map.put("startRow", startRow);
			map.put("endRow", endRow);
			map.put("id", "tlsghdyd93");
			str=sqlSession.selectList("orderSearchList0", map);
		}
		if(list_id==1) {
			map.put("startRow", startRow);
			map.put("endRow", endRow);
			map.put("id", "tlsghdyd93");
			str=sqlSession.selectList("orderSearchList1", map);
		}
		if(list_id==2) {
			map.put("startRow", startRow);
			map.put("endRow", endRow);
			map.put("id", "tlsghdyd93");
			str=sqlSession.selectList("orderSearchList2", map);
		}
		
		return str;
	}

	@Override
	public List<OrderDto> orderingList(int startRow, int endRow, int list_id) {
		Map<String, Object> map=new HashMap<String, Object>();
		List<OrderDto> str=null;
		if(list_id==0) {
			map.put("startRow", startRow);
			map.put("endRow", endRow);
			map.put("id", "tlsghdyd93");
			str=sqlSession.selectList("orderingList0", map);
		}
		if(list_id==1) {
			map.put("startRow", startRow);
			map.put("endRow", endRow);
			map.put("id", "tlsghdyd93");
			str=sqlSession.selectList("orderingList1", map);
		}
		if(list_id==2) {
			map.put("startRow", startRow);
			map.put("endRow", endRow);
			map.put("id", "tlsghdyd93");
			str=sqlSession.selectList("orderingList2", map);
		}
		
		return str;
	}

	@Override
	public List<OrderDto> deliveryList(int startRow, int endRow, int list_id) {
		Map<String, Object> map=new HashMap<String, Object>();
		List<OrderDto> str=null;
		if(list_id==0) {
			map.put("startRow", startRow);
			map.put("endRow", endRow);
			map.put("id", "tlsghdyd93");
			str=sqlSession.selectList("deliveryList0", map);
		}
		if(list_id==1) {
			map.put("startRow", startRow);
			map.put("endRow", endRow);
			map.put("id", "tlsghdyd93");
			str=sqlSession.selectList("deliveryList1", map);
		}
		if(list_id==2) {
			map.put("startRow", startRow);
			map.put("endRow", endRow);
			map.put("id", "tlsghdyd93");
			str=sqlSession.selectList("deliveryList2", map);
		}
		
		return str;
	}

	@Override
	public List<OrderDto> cancelList(int startRow, int endRow, int list_id) {
		Map<String, Object> map=new HashMap<String, Object>();
		List<OrderDto> str=null;
		if(list_id==0) {
			map.put("startRow", startRow);
			map.put("endRow", endRow);
			map.put("id", "tlsghdyd93");
			str=sqlSession.selectList("cancelList0", map);
		}
		if(list_id==1) {
			map.put("startRow", startRow);
			map.put("endRow", endRow);
			map.put("id", "tlsghdyd93");
			str=sqlSession.selectList("cancelList1", map);
		}
		if(list_id==2) {
			map.put("startRow", startRow);
			map.put("endRow", endRow);
			map.put("id", "tlsghdyd93");
			str=sqlSession.selectList("cancelList2", map);
		}
		
		return str;
	}

	@Override
	public int getBuyListCount() {
		String id="tlsghdyd93";
		return sqlSession.selectOne("buyListCount", id);
	}

	@Override
	public List<OrderDto> buyListList(int startRow, int endRow, int list_id) {
		Map<String, Object> map=new HashMap<String, Object>();
		List<OrderDto> str=null;
		if(list_id==0) {
			map.put("startRow", startRow);
			map.put("endRow", endRow);
			map.put("id", "tlsghdyd93");
			str=sqlSession.selectList("buyListList0", map);
		}
		if(list_id==1) {
			map.put("startRow", startRow);
			map.put("endRow", endRow);
			map.put("id", "tlsghdyd93");
			str=sqlSession.selectList("buyListList1", map);
		}
		if(list_id==2) {
			map.put("startRow", startRow);
			map.put("endRow", endRow);
			map.put("id", "tlsghdyd93");
			str=sqlSession.selectList("buyListList2", map);
		}
		
		return str;
	}
	
	@Override
	public int getCartCount() {
		String id="tlsghdyd93";
		return sqlSession.selectOne("cartCount", id);
	}
	
	@Override
	public List<CartDto> cartList(int startRow, int endRow, int list_id) {
		Map<String, Object> map=new HashMap<String, Object>();
		List<CartDto> str=null;
		if(list_id==0) {
			map.put("startRow", startRow);
			map.put("endRow", endRow);
			map.put("id", "tlsghdyd93");
			str=sqlSession.selectList("cartList0", map);
		}
		if(list_id==1) {
			map.put("startRow", startRow);
			map.put("endRow", endRow);
			map.put("id", "tlsghdyd93");
			str=sqlSession.selectList("cartList1", map);
		}
		if(list_id==2) {
			map.put("startRow", startRow);
			map.put("endRow", endRow);
			map.put("id", "tlsghdyd93");
			str=sqlSession.selectList("cartList2", map);
		}
		
		return str;
	}
	
	@Override
	public int insertCart(String isbn, String cart_amount) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("isbn", isbn);
		map.put("cart_amount", cart_amount);
		map.put("id", "tlsghdyd93");
		
		int count=sqlSession.selectOne("cartSelect", map);
		LogAspect.logger.info(LogAspect.logMsg+"count:" + count);
		int check=0;
		
		if(count==0) {
			check=sqlSession.insert("insertCart", map);
		}else if(count>0) {
			check=0;
		}
		return check;
	}
	
	@Override
	public int cartListDelete(String isbn) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("isbn", isbn);
		map.put("id", "tlsghdyd93");
		return sqlSession.delete("cartListDelete", map);
	}
	
	@Override
	public int statusChange(String order_number, String status) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("order_number", order_number);
		map.put("status", status);
		map.put("id", "tlsghdyd93");
		return sqlSession.update("statusChange", map);
	}
	
	@Override
	public int orderDelete(String order_number) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("order_number", order_number);
		map.put("id", "tlsghdyd93");
		return sqlSession.delete("orderDelete", map);
	}
	
	@Override
	public OrderDto getDetailOrder(String order_number) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("order_number", order_number);
		map.put("id", "tlsghdyd93");
		return (OrderDto) sqlSession.selectList("getDetailOrder", map);
	}
	
	@Override
	public String getDetailTitle(String isbn) {
		
		return sqlSession.selectOne("getDetailTitle", isbn);
	}
	
	@Override
	public long getDetailPrice(String isbn) {
		return sqlSession.selectOne("getDetailPrice", isbn);
	}
	
	@Override
	public Date getorderDate(String order_number) {
		return sqlSession.selectOne("getorderDate", order_number);
	}
}
