package com.innodale.weather.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innodale.weather.dao.ShortForecastDao;
import com.innodale.weather.dto.ObsWeather;
import com.innodale.weather.dto.ShortForecast;

import utils.HourMethods;

@Service
public class ShortForecastService {
	@Autowired
	private ShortForecastDao shortForecastDao;

	public List<ShortForecast> getShort1H(String place) {
		String[] addr = HourMethods.cutAddress(place);
		return shortForecastDao.getShort1H(addr);
	}

	public ShortForecast getStatus1H(String place) {
		String[] addr = HourMethods.cutAddress(place);
		return shortForecastDao.getStatus1H(addr);
	}
	
	public List<ShortForecast> getPastDayAccu(String place, String shortInitDt) {
		String[] addr = HourMethods.cutAddress(place);
		return shortForecastDao.getPastDayAccu(shortInitDt, addr[0]);
	}
	
}
