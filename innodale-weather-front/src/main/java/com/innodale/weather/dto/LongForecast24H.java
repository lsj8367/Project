package com.innodale.weather.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LongForecast24H {
	private String longInitDt;
	private String fcstDt;
	private int nx;
	private int ny;
	private int rainProb24h;
	private int temp24h;
	private float rain24h;
	private String sky;
	private int bodyTemp24h;
	private int hum24h;
	private int windDir;
	private float windSpeed;
	private String status;
	private String windStr;
}
