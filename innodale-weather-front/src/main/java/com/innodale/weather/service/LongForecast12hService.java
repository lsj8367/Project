package com.innodale.weather.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innodale.weather.dao.LongForecast12hDao;
import com.innodale.weather.dto.LongForecast12H;

import static utils.HourMethods.*;

@Service
public class LongForecast12hService {
	@Autowired
	private LongForecast12hDao longForecast12hDao;
	
	public void getLongForecast12H(List<LongForecast12H> list, List<String> absoList, String place){
		List<String> nowList = new ArrayList<>(); //현재시간기준 간격 yyyyMMddHH
		
		getDatas(12, nowList, absoList); //nowlist absoList에 값 들어감
		
		String[] addr = cutAddress(place);

		for(int i = 0; i < nowList.size(); i++) {
			if(addr[1].equals("여수시") || addr[1].equals("목포시") || addr[1].equals("춘천")
					|| addr[1].equals("원주") || addr[1].equals("속초") || addr[1].equals("강릉")) {
				list.add(longForecast12hDao.getLongForecast12HJeon(addr[1], nowList.get(i)));
			}else {
				if(addr[0].equals("전남")){
					list.add(longForecast12hDao.getLongForecast12HJeon("여수시", nowList.get(i)));
				}else if(addr[0].equals("강원")) {
					list.add(longForecast12hDao.getLongForecast12HJeon("원주시", nowList.get(i)));
				}else {
					list.add(longForecast12hDao.getLongForecast12H(addr[0], nowList.get(i)));					
				}
			}
		}
		
		if(list.get(0) == null) {
			// 02, 05, 08, 11, 14, 17, 20, 23
			String ss = todayYMD(absoList.get(0));
			list.remove(0);
			list.add(0, longForecast12hDao.getNotEqualForecast12H(ss));
		}
	}
}
