package com.innodale.weather.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innodale.weather.dao.LongForecast3hDao;
import com.innodale.weather.dto.LongForecast3H;

import lombok.extern.slf4j.Slf4j;
import utils.HourMethods;

@Service
public class LongForecast3hService {
	
	@Autowired
	private LongForecast3hDao longForecast3hDao;
	
	public void getLongForecast3H(List<LongForecast3H> list, List<String> absoList, String place){
		
		List<String> nowList = new ArrayList<>(); //현재시간기준 간격 yyyyMMddHH
		
		HourMethods.getDatas(3, nowList, absoList); //nowlist absoList에 값 들어감
		
		String[] addr = HourMethods.cutAddress(place);
		for(int i = 0; i < nowList.size(); i++) {
			if(addr[1].equals("여수시") || addr[1].equals("목포시") || addr[1].equals("춘천")
					|| addr[1].equals("원주") || addr[1].equals("속초") || addr[1].equals("강릉")) {
				list.add(longForecast3hDao.getLongForecast3HJeon(addr[1], nowList.get(i)));
			}else {
				if(addr[0].equals("전남")){
					list.add(longForecast3hDao.getLongForecast3HJeon("여수시", nowList.get(i)));
				}else if(addr[0].equals("강원")) {
					list.add(longForecast3hDao.getLongForecast3HJeon("원주시", nowList.get(i)));
				}else {
					list.add(longForecast3hDao.getLongForecast3H(addr[0], nowList.get(i)));
				}				
			}
		}
		if(list.get(0) == null) {
			// 02, 05, 08, 11, 14, 17, 20, 23
			String ss = HourMethods.todayYMD(absoList.get(0));
			list.remove(0);
			list.add(0, longForecast3hDao.getNotEqualForecast3H(ss));
		}
	}
}
