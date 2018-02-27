package com.team3.admin.sales.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.team3.admin.sales.dto.SalesDto;
import com.team3.aop.LogAspect;

@Component
public class SalesDaoImp implements SalesDao {
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<SalesDto> salesSelect(String value) {
		List<SalesDto> salesList=new ArrayList<SalesDto>();
		List<SalesDto> month=null;
		LogAspect.logger.info(LogAspect.logMsg + value);
		if(value.equals("1")) {
			salesList=sqlSession.selectList("selectSales");
		}else if(value.equals("2")) {
			month=sqlSession.selectList("selectMonth");
			for(int i=0;i<month.size();i++) {
				SalesDto dto=sqlSession.selectOne("selectMonthDto",month.get(i).getSales_day());
				dto.setSales_day(month.get(i).getSales_day());
				salesList.add(dto);
//				salesList.set(i, (SalesDto) sqlSession.selectOne("selectDto",month.get(i).getSales_day()));
				
			}
			LogAspect.logger.info(LogAspect.logMsg + salesList.toString());
		}else if(value.equals("3")) {
			month=sqlSession.selectList("selectYear");
			for(int i=0;i<month.size();i++) {
				SalesDto dto=sqlSession.selectOne("selectYearDto",month.get(i).getSales_day());
				dto.setSales_day(month.get(i).getSales_day());
				salesList.add(dto);
//				salesList.set(i, (SalesDto) sqlSession.selectOne("selectDto",month.get(i).getSales_day()));
				
			}
			LogAspect.logger.info(LogAspect.logMsg + salesList.toString());
		}
		return salesList;
	}
	
	
	
}
