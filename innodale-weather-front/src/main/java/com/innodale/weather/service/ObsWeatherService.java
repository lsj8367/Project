package com.innodale.weather.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innodale.weather.dao.ObsWeatherDao;
import com.innodale.weather.dto.ObsWeather;

import utils.HourMethods;

@Service
public class ObsWeatherService {
	
	@Autowired
	private ObsWeatherDao obsWeatherDao;
	
	public List<ObsWeather> getObsWeather(String place){
		String[] addr = HourMethods.cutAddress(place);
		return obsWeatherDao.getObsWeather(addr);
	}
	
	public List<ObsWeather> getMapWeather(){	
		return obsWeatherDao.getMapWeather();
	}
	
	public ObsWeather getTodayAccu(String place) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		
		String[] addr = HourMethods.cutAddress(place);
		return obsWeatherDao.getTodayAccu(sdf.format(date), addr[0]);
	}
}
