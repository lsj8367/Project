package com.innodale.weather.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.innodale.weather.dto.ObsWeather;
import com.innodale.weather.dto.ShortForecast;

@Repository
public class ShortForecastDao extends SqlSessionDaoSupport{
	
	@Autowired
	public ShortForecastDao(SqlSessionFactory factory) {
		setSqlSessionFactory(factory);
	}
	
	private final String namespace = "com.innodale.weather.shortforecast.";
	
	//현재+1시간
	public List<ShortForecast> getShort1H(String[] addr) {
		return getSqlSession().selectList(namespace+"getShort1H", addr[0]);
	}
	
	public ShortForecast getStatus1H(String[] addr) {
		return getSqlSession().selectOne(namespace+"getStatus1H",addr[0]);
	}
	
	public List<ShortForecast> getPastDayAccu(String shortInitDt, String city) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("shortInitDt", shortInitDt);
		map.put("city", city);
		return getSqlSession().selectList(namespace + "getPastDayAccu", map); 
	}
	
}
