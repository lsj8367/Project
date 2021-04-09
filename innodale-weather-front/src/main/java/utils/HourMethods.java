package utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;


public class HourMethods {
	
	// 현재시간기준으로 시간간격 리스트, api 절대시간 시간간격 리스트
	public static void getDatas(int sigan, List<String> nowList, List<String> absoList) {
		
		Logger logger = Logger.getGlobal();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHH");
		
		Date dt1 = new Date();
		
		String time = format.format(dt1);

		Date dt2 = HourMethods.getDate(Integer.parseInt(time.substring(0, 4)),
						Integer.parseInt(time.substring(4, 6)),
						Integer.parseInt(time.substring(6, 8)));
		
		String today00 = format.format(dt2);
		
		List<Date> list = Optional.ofNullable(HourMethods.createHours(sigan, today00))
								  .orElseGet(ArrayList::new); //6시간 절대 시간
		
		List<Date> list2 = Optional.ofNullable(HourMethods.createHours(sigan, time))
								   .orElseGet(ArrayList::new); //유동시간 리스트

		while(list.get(0).compareTo(dt1) < 0) { // 0번째가 현재시간보다 값이 작으면 제거
			list.remove(0);
		}
		
//		for(Date a : list) {
//			nowList.add(format.format(a));
//		}
		
		list.stream()
			.map(dt -> nowList.add(format.format(dt)))
			.forEach(a -> logger.info("Create list: " + a));
		
		
		for(int i = 0; i < list2.size(); i++) {
			if(list.get(list.size() - 1).compareTo(list2.get(i)) <= 0) { 
				list2.subList(i, list2.size()).clear(); // 현재시간으로 구하는 시간뒤 값이 3일뒤 00시값보다 크면 그 뒤로 전부제거
				break;
			}
		}
		
		list2.stream()
			 .map(a -> absoList.add(format.format(a)))
			 .forEach(a -> logger.info("Create list2 : " + a));
		
	}
	public static String todayYMD(String time) {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String ss = format.format(date);
		List<String> list = new ArrayList<>();
		list.add("02");
		list.add("05");
		list.add("08");
		list.add("11");
		list.add("14");
		list.add("17");
		list.add("20");
		list.add("23");
		String a = ""; 
		// 02, 05, 08, 11, 14, 17, 20, 23
		for(int i = 0; i < list.size(); i++) {
			a = ss + list.get(i);
			if (a.compareTo(time) <= -3 || a.compareTo(time) == 0) {
				return a;
			}
		}
		return "";
	}
	
	
	public static Date getDate(int year, int month, int date) { // 오늘 00시 생성 메소드
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, date, 0, 0, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	
	public static List<Date> createHours(int time, String today) { // 24시간 간격
		List<Date> list = new ArrayList<Date>();
		int index = 0;
		Calendar cal = Calendar.getInstance();

		cal.set(Integer.parseInt(today.substring(0, 4)), Integer.parseInt(today.substring(4, 6)) - 1,
				Integer.parseInt(today.substring(6, 8)), Integer.parseInt(today.substring(8, 10)), 0, 0);

		list.add(cal.getTime()); // 현재 일 00시 한번
		
		if(time == 3) {
			index = 24;
		}else if(time == 6) {
			index = 12;
		}else if(time == 12) {
			index = 6;
		}else if(time == 24) {
			index = 3;
		}
		
		
		for (int i = 0; i < index; i++) {
			cal.add(Calendar.HOUR, +time);
			list.add(cal.getTime());
		}

		return list;
	}
	

	// 주소 DB형식에 맞게 자르기
	public static String[] cutAddress(String place) {
		place = place.substring(0, place.lastIndexOf(" ")); // 번지수 떼냄
		String[] cutAddr = place.split(" ");
		String city;
		String district;
		String dong;

		if (cutAddr.length > 3) { // 도 시 구 동 일경우에는
			city = cutAddr[0];
			district = cutAddr[1] + cutAddr[2]; // 시 + 구 붙여줌
			dong = cutAddr[3];

		} else {
			city = cutAddr[0];
			district = cutAddr[1];
			dong = cutAddr[2];
		}
		String[] newAddr = { city, district, dong };
		return newAddr;
	}
	// 전국구 까지만 자르기 서울 경기 등등
	public static String[] cutCity(String place) {
		place = place.substring(0, place.lastIndexOf(" ")); // 번지수 떼냄
		String[] cutAddr = place.split(" ");
		String city;

		if (cutAddr.length > 3) { // 도 시 구 동 일경우에는
			city = cutAddr[0];

		} else {
			city = cutAddr[0];
		}
		String[] newAddr = {city};
		return newAddr;
		
	}

}
