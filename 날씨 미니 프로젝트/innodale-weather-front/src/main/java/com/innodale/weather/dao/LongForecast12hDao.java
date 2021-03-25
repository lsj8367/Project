package com.innodale.weather.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.innodale.weather.dto.LongForecast12H;

@Repository
public class LongForecast12hDao extends SqlSessionDaoSupport{
	
	private final static String namespace = "com.innodale.weather.longForecast12H";
	
	@Autowired
	public LongForecast12hDao(SqlSessionFactory factory) {
		setSqlSessionFactory(factory);
	}
	
	
	public LongForecast12H getLongForecast12H(String city, String fcstDt) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("city", city);
		map.put("fcstDt", fcstDt);
		return getSqlSession().selectOne(namespace + ".getLongForecast12H", map);
	}
	
	public LongForecast12H getNotEqualForecast12H(String longInitDt) {
		return getSqlSession().selectOne(namespace + ".getNotEqualForecast12H", longInitDt);
	}
	
	public LongForecast12H getLongForecast12HJeon(String district, String fcstDt) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("district", district);
		map.put("fcstDt", fcstDt);
		return getSqlSession().selectOne(namespace + ".getLongForecast12HJeon", map);
	}
}
