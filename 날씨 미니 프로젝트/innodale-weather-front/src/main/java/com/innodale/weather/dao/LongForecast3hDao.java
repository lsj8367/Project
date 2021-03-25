package com.innodale.weather.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.innodale.weather.dto.LongForecast3H;

@Repository
public class LongForecast3hDao extends SqlSessionDaoSupport{
	
	private static final String namespace = "com.innodale.weather.longForecast3H";
	
	@Autowired
	public LongForecast3hDao(SqlSessionFactory factory) {
		setSqlSessionFactory(factory);
	}
	
	public LongForecast3H getLongForecast3H(String city, String fcstDt) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("city", city);
		map.put("fcstDt", fcstDt);
		return getSqlSession().selectOne(namespace + ".getLongForecast3H", map);
	}
	
	public LongForecast3H getNotEqualForecast3H(String longInitDt) {
		return getSqlSession().selectOne(namespace + ".getNotEqualForecast3H", longInitDt);
	}
	
	public LongForecast3H getLongForecast3HJeon(String district, String fcstDt) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("district", district);
		map.put("fcstDt", fcstDt);
		return getSqlSession().selectOne(namespace + ".getLongForecast3HJeon", map);
	}
}
