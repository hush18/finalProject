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
	public int getOrderSearchCount(String id) {
		return sqlSession.selectOne("com.team3.user.order.dao.mapper.orderSearchCount", id);
	}
	
	@Override
	public int getOrderingCount(String id) {
		return sqlSession.selectOne("com.team3.user.order.dao.mapper.orderingCount", id);
	}

	@Override
	public int getDeliveryCount(String id) {
		return sqlSession.selectOne("com.team3.user.order.dao.mapper.deliveryCount", id);
	}

	@Override
	public int getCancelCount(String id) {
		return sqlSession.selectOne("com.team3.user.order.dao.mapper.cancelCount", id);
	}

	@Override
	public int getPoint(String id) {
		return sqlSession.selectOne("com.team3.user.order.dao.mapper.point", id);
	}
	
	@Override
	public List<OrderDto> orderSearchList(int startRow, int endRow, int list_id, String id, String dateValue, String dateValueList) {
		Map<String, Object> map=new HashMap<String, Object>();
		List<OrderDto> str=null;
	
		if(dateValueList.equals("0")) {				
			String value=null;
			switch(Integer.parseInt(dateValue)) {
				case 0: value="10000"; break;
				case 1: value="15"; break;
				case 2: value="30"; break;
				case 3: value="90"; break;
				case 4: value="180"; break;
				default: break;
			}
			map.put("startRow", startRow);
			map.put("endRow", endRow);
			map.put("id", id);
			map.put("value", value);
			
			if(list_id==0) {
				str=sqlSession.selectList("com.team3.user.order.dao.mapper.orderSearchList0", map);
			}
			if(list_id==1) {
				str=sqlSession.selectList("com.team3.user.order.dao.mapper.orderSearchList1", map);
			}
			if(list_id==2) {
				str=sqlSession.selectList("com.team3.user.order.dao.mapper.orderSearchList2", map);
			}
		}else {
			String[] valueList=dateValueList.split("/");
			String from_date=valueList[0];
			String to_date=valueList[1];
			
			map.put("startRow", startRow);
			map.put("endRow", endRow);
			map.put("id", id);
			map.put("from_date", from_date);
			map.put("to_date", to_date);
			if(list_id==0) {
				str=sqlSession.selectList("com.team3.user.order.dao.mapper.orderSearchList3", map);
			}
			if(list_id==1) {
				str=sqlSession.selectList("com.team3.user.order.dao.mapper.orderSearchList4", map);
			}
			if(list_id==2) {
				str=sqlSession.selectList("com.team3.user.order.dao.mapper.orderSearchList5", map);
			}
		}
		
		return str;
	}

	@Override
	public List<OrderDto> orderingList(int startRow, int endRow, int list_id, String id) {
		Map<String, Object> map=new HashMap<String, Object>();
		List<OrderDto> str=null;
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		map.put("id", id);
		
		if(list_id==0) {
			str=sqlSession.selectList("com.team3.user.order.dao.mapper.orderingList0", map);
		}
		if(list_id==1) {
			str=sqlSession.selectList("com.team3.user.order.dao.mapper.orderingList1", map);
		}
		if(list_id==2) {
			str=sqlSession.selectList("com.team3.user.order.dao.mapper.orderingList2", map);
		}
		
		return str;
	}

	@Override
	public List<OrderDto> deliveryList(int startRow, int endRow, int list_id, String id) {
		Map<String, Object> map=new HashMap<String, Object>();
		List<OrderDto> str=null;
		
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		map.put("id", id);
		
		if(list_id==0) {
			str=sqlSession.selectList("com.team3.user.order.dao.mapper.deliveryList0", map);
		}
		if(list_id==1) {
			str=sqlSession.selectList("com.team3.user.order.dao.mapper.deliveryList1", map);
		}
		if(list_id==2) {
			str=sqlSession.selectList("com.team3.user.order.dao.mapper.deliveryList2", map);
		}
		
		return str;
	}

	@Override
	public List<OrderDto> cancelList(int startRow, int endRow, int list_id, String id, String dateValue, String dateValueList) {
		Map<String, Object> map=new HashMap<String, Object>();
		List<OrderDto> str=null;
		if(dateValueList.equals("0")) {
			String value=null;
			switch(Integer.parseInt(dateValue)) {
				case 0: value="10000"; break;
				case 1: value="15"; break;
				case 2: value="30"; break;
				case 3: value="90"; break;
				case 4: value="180"; break;
				default: break;
			}
			map.put("startRow", startRow);
			map.put("endRow", endRow);
			map.put("id", id);
			map.put("value", value);
			
			if(list_id==0) {
				str=sqlSession.selectList("com.team3.user.order.dao.mapper.cancelList0", map);
			}
			if(list_id==1) {
				str=sqlSession.selectList("com.team3.user.order.dao.mapper.cancelList1", map);
			}
			if(list_id==2) {
				str=sqlSession.selectList("com.team3.user.order.dao.mapper.cancelList2", map);
			}
		}else {
			String[] valueList=dateValueList.split("/");
			String from_date=valueList[0];
			String to_date=valueList[1];
			
			map.put("startRow", startRow);
			map.put("endRow", endRow);
			map.put("id", id);
			map.put("from_date", from_date);
			map.put("to_date", to_date);
			
			if(list_id==0) {
				str=sqlSession.selectList("com.team3.user.order.dao.mapper.cancelList3", map);
			}
			if(list_id==1) {
				str=sqlSession.selectList("com.team3.user.order.dao.mapper.cancelList4", map);
			}
			if(list_id==2) {
				str=sqlSession.selectList("com.team3.user.order.dao.mapper.cancelList5", map);
			}
		}
		return str;
	}

	@Override
	public int getBuyListCount(String id) {
		return sqlSession.selectOne("buyListCount", id);
	}

	@Override
	public List<OrderDto> buyListList(int startRow, int endRow, int list_id, String id, String dateValue, String dateValueList) {
		Map<String, Object> map=new HashMap<String, Object>();
		List<OrderDto> str=null;
		if(dateValueList.equals("0")) {
			String value=null;
			switch(Integer.parseInt(dateValue)) {
				case 0: value="10000"; break;
				case 1: value="15"; break;
				case 2: value="30"; break;
				case 3: value="90"; break;
				case 4: value="180"; break;
				default: break;
			}
			
			map.put("startRow", startRow);
			map.put("endRow", endRow);
			map.put("id", id);
			map.put("value", value);
			
			if(list_id==0) {
				str=sqlSession.selectList("com.team3.user.order.dao.mapper.buyListList0", map);
			}
			if(list_id==1) {
				str=sqlSession.selectList("com.team3.user.order.dao.mapper.buyListList1", map);
			}
			if(list_id==2) {
				str=sqlSession.selectList("com.team3.user.order.dao.mapper.buyListList2", map);
			}
		}else {
			String[] valueList=dateValueList.split("/");
			String from_date=valueList[0];
			String to_date=valueList[1];
			
			map.put("startRow", startRow);
			map.put("endRow", endRow);
			map.put("id", id);
			map.put("from_date", from_date);
			map.put("to_date", to_date);
			
			if(list_id==0) {
				str=sqlSession.selectList("com.team3.user.order.dao.mapper.buyListList3", map);
			}
			if(list_id==1) {
				str=sqlSession.selectList("com.team3.user.order.dao.mapper.buyListList4", map);
			}
			if(list_id==2) {
				str=sqlSession.selectList("com.team3.user.order.dao.mapper.buyListList5", map);
			}
		}
		
		return str;
	}
	
	@Override
	public int getCartCount(String id) {
		return sqlSession.selectOne("com.team3.user.order.dao.mapper.cartCount", id);
	}
	
	@Override
	public List<CartDto> cartList(int startRow, int endRow, int list_id, String id) {
		Map<String, Object> map=new HashMap<String, Object>();
		List<CartDto> str=null;
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		map.put("id", id);
		
		if(list_id==0) {
			str=sqlSession.selectList("com.team3.user.order.dao.mapper.cartList0", map);
		}
		if(list_id==1) {
			str=sqlSession.selectList("com.team3.user.order.dao.mapper.cartList1", map);
		}
		if(list_id==2) {
			str=sqlSession.selectList("com.team3.user.order.dao.mapper.cartList2", map);
		}
		
		return str;
	}
	
	@Override
	public int insertCart(String isbn, String cart_amount, String id) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("isbn", isbn);
		map.put("cart_amount", cart_amount);
		map.put("id", id);
		
		int count=sqlSession.selectOne("com.team3.user.order.dao.mapper.cartSelect", map);
		LogAspect.logger.info(LogAspect.logMsg+"count:" + count);
		int check=0;
		
		if(count==0) {
			check=sqlSession.insert("com.team3.user.order.dao.mapper.insertCart", map);
		}else if(count>0) {
			check=0;
		}
		return check;
	}
	
	@Override
	public int cartListDelete(String isbn, String id) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("isbn", isbn);
		map.put("id", id);
		return sqlSession.delete("com.team3.user.order.dao.mapper.cartListDelete", map);
	}
	
	@Override
	public int statusChange(String order_number, String status, String id) {
		Map<String, Object> map=new HashMap<String, Object>();
		
		map.put("order_number", order_number);
		map.put("status", status);
		map.put("id", id);
		int value=0;
		LogAspect.logger.info(LogAspect.logMsg + id);
		if(id.equals("admin")) {
			value=sqlSession.update("com.team3.admin.order.dao.mapper.adminStatusChange",map);
		}else {
			value=sqlSession.update("com.team3.user.order.dao.mapper.statusChange", map);			
		}
		return value;
	}
	
	@Override
	public int orderDelete(String order_number, String id) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("order_number", order_number);
		map.put("id", id);
		return sqlSession.delete("com.team3.user.order.dao.mapper.orderDelete", map);
	}
	
	@Override
	public List<OrderDto> getDetailOrder(String order_number, String id) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("order_number", order_number);
		map.put("id", id);
		return sqlSession.selectList("com.team3.user.order.dao.mapper.getDetailOrder", map);
	}
	
	@Override
	public long getDetailPrice(String isbn) {
		return sqlSession.selectOne("com.team3.user.order.dao.mapper.getDetailPrice", isbn);
	}
	
	@Override
	public Date getOrderDate(String order_number) {
		return sqlSession.selectOne("com.team3.user.order.dao.mapper.getOrderDate", order_number);
	}
	
	@Override
	public String getTitle(String isbn) {
		return sqlSession.selectOne("com.team3.user.order.dao.mapper.getOrderTitle", isbn);
	}
	
	@Override
	public String getOrder_name(String id) {
		return sqlSession.selectOne("com.team3.user.order.dao.mapper.getOrder_name", id);
	}
	
	@Override
	public OrderDto getOrderInfo(String order_number, String id) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("order_number", order_number);
		map.put("id", id);
		return sqlSession.selectOne("com.team3.user.order.dao.mapper.getOrderInfo", map);
	}
	
	@Override
	public int getUse_point(String order_number) {
		return sqlSession.selectOne("com.team3.user.order.dao.mapper.getUse_point", order_number);
	}
}

