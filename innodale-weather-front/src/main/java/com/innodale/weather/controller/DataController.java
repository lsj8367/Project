package com.innodale.weather.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.innodale.weather.dto.LongForecast12H;
import com.innodale.weather.dto.LongForecast24H;
import com.innodale.weather.dto.LongForecast3H;
import com.innodale.weather.dto.LongForecast6H;
import com.innodale.weather.dto.ObsWeather;
import com.innodale.weather.dto.ShortForecast;
import com.innodale.weather.service.LongForecast12hService;
import com.innodale.weather.service.LongForecast24hService;
import com.innodale.weather.service.LongForecast3hService;
import com.innodale.weather.service.LongForecast6hService;
import com.innodale.weather.service.ObsWeatherService;
import com.innodale.weather.service.ShortForecastService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class DataController {
	
	private final ObsWeatherService obsWeatherService;
	private final ShortForecastService shortForecastService;
	private final LongForecast3hService longForecast3hService;
	private final LongForecast6hService longForecast6hService;
	private final LongForecast12hService longForecast12hService;
	private final LongForecast24hService longForecast24hService;

	@GetMapping("/ObsWeather/{place}")
	public Map<String,Object> getObsWeather(@PathVariable("place") String place){
		Map<String, Object> map = new HashMap<String, Object>(); 
		List<ObsWeather> list = obsWeatherService.getObsWeather(place);
		ShortForecast list2 = shortForecastService.getStatus1H(place); 
		map.put("list", list);
		map.put("list2",list2);
		map.put("getTodayAccu", obsWeatherService.getTodayAccu(place));
		return map;
	}
	
	// 1시간마다 정보
	@GetMapping("/ShortForecast/{place}") 
	public List<ShortForecast> getShort(@PathVariable("place") String place){
		
		return shortForecastService.getShort1H(place);
	}
	
	//3시간 간격
	@GetMapping("/LongForecast3H/{place}")
	public Map<String, Object> getLong3H(@PathVariable("place") String place){
		Map<String, Object> map = new HashMap<String, Object>();
		List<LongForecast3H> list = new ArrayList<>();
		List<String> absoList = new ArrayList<>();
		longForecast3hService.getLongForecast3H(list, absoList, place);
		map.put("list", list);
		map.put("date", absoList);
		return map;
	}
	
	//6시간 간격
	@GetMapping("/LongForecast6H/{place}")
	public Map<String, Object> getLong6H(@PathVariable("place") String place){
		Map<String, Object> map = new HashMap<String, Object>();
		List<LongForecast6H> list = new ArrayList<>();
		List<String> absoList = new ArrayList<>();
		longForecast6hService.getLongForecast6H(list, absoList, place);
		map.put("list", list);
		map.put("date", absoList);
		return map;
	}
	
	//12시간 간격
	@GetMapping("/LongForecast12H/{place}")
	public Map<String, Object> getLong12H(@PathVariable("place") String place){
		Map<String, Object> map = new HashMap<String, Object>();
		List<LongForecast12H> list = new ArrayList<>();
		List<String> absoList = new ArrayList<>();
		longForecast12hService.getLongForecast12H(list, absoList, place);
		map.put("list", list);
		map.put("date", absoList);
		return map;
	}
	
	//24시간 간격
	@GetMapping("/LongForecast24H/{place}")
	public Map<String, Object> getLong24H(@PathVariable("place") String place){
		Map<String, Object> map = new HashMap<String, Object>();
		List<LongForecast24H> list = new ArrayList<>();
		List<String> absoList = new ArrayList<>();
		longForecast24hService.getLongForecast24H(list, absoList, place);
		map.put("list", list);
		map.put("date", absoList);
		
		return map;
	}
	
	//지도
	@GetMapping("/reMarker")
	public List<ObsWeather> getMapWeather(){
		List<ObsWeather> list = obsWeatherService.getMapWeather();
		return list;
	}
	
	//과거날씨
	@PostMapping("/PastWeather/{place}")
	public List<ShortForecast> getPastWeather(@PathVariable("place") String place, @RequestBody Map<String, Object> shortInitDt){

		return shortForecastService.getPastDayAccu(place, shortInitDt.get("shortInitDt").toString());	
	}
	
}
