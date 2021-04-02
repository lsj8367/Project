package com.innodale.weather.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.innodale.weather.dto.LongForecast6H;

@Repository
public class LongForecast6hDao extends SqlSessionDaoSupport{
	private final static String namespace = "com.innodale.weather.longForecast6H";
	
	@Autowired
	public LongForecast6hDao(SqlSessionFactory factory) {
		setSqlSessionFactory(factory);
	}
	
	public LongForecast6H getLongForecast6H(String city, String fcstDt) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("city", city);
		map.put("fcstDt", fcstDt);
		return getSqlSession().selectOne(namespace + ".getLongForecast6H", map);
	}
	
	public LongForecast6H getNotEqualForecast6H(String longInitDt) {
		return getSqlSession().selectOne(namespace + ".getNotEqualForecast6H", longInitDt);
	}
	
	public LongForecast6H getLongForecast6HJeon(String district, String fcstDt) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("district", district);
		map.put("fcstDt", fcstDt);
		return getSqlSession().selectOne(namespace + ".getLongForecast6HJeon", map);
	}
}
