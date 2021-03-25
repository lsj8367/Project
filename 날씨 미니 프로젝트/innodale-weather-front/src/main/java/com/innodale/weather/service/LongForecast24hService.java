package com.innodale.weather.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innodale.weather.dao.LongForecast24hDao;
import com.innodale.weather.dto.LongForecast24H;

import utils.HourMethods;

@Service
public class LongForecast24hService {
	@Autowired
	private LongForecast24hDao longForecast24hDao;
	
	public void getLongForecast24H(List<LongForecast24H> list, List<String> absoList, String place){
		List<String> nowList = new ArrayList<>(); //현재시간기준 간격 yyyyMMddHH
		
		HourMethods.getDatas(24, nowList, absoList); //nowlist absoList에 값 들어감
		
		String[] addr = HourMethods.cutAddress(place);

		for(int i = 0; i < nowList.size(); i++) {
			if(addr[1].equals("여수시") || addr[1].equals("목포시") || addr[1].equals("춘천")
					|| addr[1].equals("원주") || addr[1].equals("속초") || addr[1].equals("강릉")) {
				list.add(longForecast24hDao.getLongForecast24HJeon(addr[1], nowList.get(i)));
			}else {
				if(addr[0].equals("전남")){
					list.add(longForecast24hDao.getLongForecast24HJeon("여수시", nowList.get(i)));
				}else if(addr[0].equals("강원")) {
					list.add(longForecast24hDao.getLongForecast24HJeon("원주시", nowList.get(i)));
				}else {
					list.add(longForecast24hDao.getLongForecast24H(addr[0], nowList.get(i)));					
				}
			}
		}
		if(list.get(0) == null) {
			// 02, 05, 08, 11, 14, 17, 20, 23
			String ss = HourMethods.todayYMD(absoList.get(0));
			list.add(0, longForecast24hDao.getNotEqualForecast24H(ss));
		}
	}

}
