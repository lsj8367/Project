package com.innodale.weather.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LongForecast3H {
	private String longInitDt;
	private String fcstDt;
	private int rainProb3h;
	private int temp3h;
	private float rain3h;
	private String sky;
	private int bodyTemp3h;
	private int hum3h;
	private int windDir;
	private float windSpeed;
	private int nx;
	private int ny;
	private String status;
	private String windStr;
}
