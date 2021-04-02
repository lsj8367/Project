package com.innodale.weather.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innodale.weather.dao.ShortForecastDao;
import com.innodale.weather.dto.ObsWeather;
import com.innodale.weather.dto.ShortForecast;

import lombok.RequiredArgsConstructor;
import utils.HourMethods;

@Service
@RequiredArgsConstructor
public class ShortForecastService {
	
	private final ShortForecastDao shortForecastDao;

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
