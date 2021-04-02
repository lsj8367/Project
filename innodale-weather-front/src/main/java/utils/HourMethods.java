package utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;


public class HourMethods {
	
	// 현재시간기준으로 시간간격 리스트, api 절대시간 시간간격 리스트
	public static void getDatas(int sigan, List<String> nowList, List<String> absoList) {
		
		Logger logger = Logger.getGlobal();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHH");
		
		List<Date> list = new ArrayList<>();  //6시간 절대 시간
		List<Date> list2 = new ArrayList<>();  //유동시간 리스트	
		
		Date dt1 = new Date();
		
		String time = format.format(dt1);

		Date dt2 = HourMethods.getDate(Integer.parseInt(time.substring(0, 4)),
						Integer.parseInt(time.substring(4, 6)),
						Integer.parseInt(time.substring(6, 8)));
		
		String today00 = format.format(dt2);
		
		if(sigan == 3) {
			list = HourMethods.get3H(today00);
			list2 = HourMethods.get3H(time);
			
		}else if(sigan == 6) {
			list = HourMethods.get6H(today00);
			list2 = HourMethods.get6H(time);
			
		}else if(sigan == 12) {
			list = HourMethods.get12H(today00);
			list2 = HourMethods.get12H(time);
			
		}else if(sigan == 24) {
			list = HourMethods.get24H(today00);
			list2 = HourMethods.get24H(time);		
		}
		
		
		while(list.get(0).compareTo(dt1) < 0) { // 0번째가 현재시간보다 값이 작으면 제거
			list.remove(0);
		}
		
//		for(Date a : list) {
//			nowList.add(format.format(a));
//		}
		
		list.stream()
			.map(dt -> nowList.add(format.format(dt)))
			.forEach(a -> logger.info(a.toString()));
		
		
		for(int i = 0; i < list2.size(); i++) {
			if(list.get(list.size() - 1).compareTo(list2.get(i)) <= 0) { 
				list2.subList(i, list2.size()).clear(); // 현재시간으로 구하는 시간뒤 값이 3일뒤 00시값보다 크면 그 뒤로 전부제거
				break;
			}
		}
		
		list2.stream()
			 .map(a -> absoList.add(format.format(a)))
			 .forEach(a -> logger.info(a.toString()));
		
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

	public static List<Date> get3H(String today) { // 3시간 간격
		List<Date> list = new ArrayList<Date>();
		Calendar cal = Calendar.getInstance();

		cal.set(Integer.parseInt(today.substring(0, 4)), Integer.parseInt(today.substring(4, 6)) - 1,
				Integer.parseInt(today.substring(6, 8)), Integer.parseInt(today.substring(8, 10)), 0, 0);
		cal.set(Calendar.MILLISECOND, 0);

		list.add(cal.getTime()); // 현재 일 00시 한번

		for (int i = 0; i < 24; i++) {
			cal.add(Calendar.HOUR, +3);
			list.add(cal.getTime());
		}

		return list;
	}

	public static List<Date> get6H(String today) { // 6시간 간격
		List<Date> list = new ArrayList<Date>();
		Calendar cal = Calendar.getInstance();

		cal.set(Integer.parseInt(today.substring(0, 4)), Integer.parseInt(today.substring(4, 6)) - 1,
				Integer.parseInt(today.substring(6, 8)), Integer.parseInt(today.substring(8, 10)), 0, 0);

		list.add(cal.getTime()); // 현재 일 00시 한번

		for (int i = 0; i < 12; i++) {
			cal.add(Calendar.HOUR, +6);
			list.add(cal.getTime());
		}

		return list;
	}

	public static List<Date> get12H(String today) { // 12시간 간격
		List<Date> list = new ArrayList<Date>();
		Calendar cal = Calendar.getInstance();

		cal.set(Integer.parseInt(today.substring(0, 4)), Integer.parseInt(today.substring(4, 6)) - 1,
				Integer.parseInt(today.substring(6, 8)), Integer.parseInt(today.substring(8, 10)), 0, 0);

		list.add(cal.getTime()); // 현재 일 00시 한번

		for (int i = 0; i < 6; i++) {
			cal.add(Calendar.HOUR, +12);
			list.add(cal.getTime());
		}

		return list;
	}

	public static List<Date> get24H(String today) { // 24시간 간격
		List<Date> list = new ArrayList<Date>();
		Calendar cal = Calendar.getInstance();

		cal.set(Integer.parseInt(today.substring(0, 4)), Integer.parseInt(today.substring(4, 6)) - 1,
				Integer.parseInt(today.substring(6, 8)), Integer.parseInt(today.substring(8, 10)), 0, 0);

		list.add(cal.getTime()); // 현재 일 00시 한번

		for (int i = 0; i < 3; i++) {
			cal.add(Calendar.HOUR, +24);
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
