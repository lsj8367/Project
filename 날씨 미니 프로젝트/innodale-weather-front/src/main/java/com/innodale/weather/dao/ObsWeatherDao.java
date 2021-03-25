package com.innodale.weather.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.innodale.weather.dto.ObsWeather;

import lombok.extern.slf4j.Slf4j;

@Repository
public class ObsWeatherDao extends SqlSessionDaoSupport{

	@Autowired
	public ObsWeatherDao(SqlSessionFactory factory) {
		setSqlSessionFactory(factory);
	}
	
	private final String namespace = "com.innodale.weather.obsweather.";
	

	public List<ObsWeather> getObsWeather(String[] addr) {
		return getSqlSession().selectList(namespace + "getObsWeater", addr[0]);
	}
	
	public List<ObsWeather> getMapWeather(){
		return getSqlSession().selectList(namespace + "getMapWeather");
	}
	
	public ObsWeather getTodayAccu(String obsDt, String city) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("obsDt", obsDt);
		map.put("city", city);
		return getSqlSession().selectOne(namespace + "getTodayAccu", map);
	}
	
}
