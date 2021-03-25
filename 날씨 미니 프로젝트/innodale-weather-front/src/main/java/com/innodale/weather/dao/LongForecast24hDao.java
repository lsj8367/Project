package com.innodale.weather.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.innodale.weather.dto.LongForecast24H;

@Repository
public class LongForecast24hDao extends SqlSessionDaoSupport{
	
	private final static String namespace = "com.innodale.weather.longForecast24H";
	
	@Autowired
	public LongForecast24hDao(SqlSessionFactory factory) {
		setSqlSessionFactory(factory);
	}

	public LongForecast24H getLongForecast24H(String city, String fcstDt) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("city", city);
		map.put("fcstDt", fcstDt);
		return getSqlSession().selectOne(namespace + ".getLongForecast24H", map);
	}
	
	public LongForecast24H getNotEqualForecast24H(String longInitDt) {
		return getSqlSession().selectOne(namespace + ".getNotEqualForecast24H", longInitDt);
	}
	
	public LongForecast24H getLongForecast24HJeon(String district, String fcstDt) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("district", district);
		map.put("fcstDt", fcstDt);
		return getSqlSession().selectOne(namespace + ".getLongForecast24HJeon", map);
	}
}
