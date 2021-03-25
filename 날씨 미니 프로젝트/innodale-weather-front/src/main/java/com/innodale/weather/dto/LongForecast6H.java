package com.innodale.weather.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LongForecast6H {
	private String longInitDt;
	private String fcstDt;
	private int nx;
	private int ny;
	private int rainProb6h;
	private int temp6h;
	private float rain6h;
	private String sky;
	private int bodyTemp6h;
	private int hum6h;
	private int windDir;
	private float windSpeed;
	private String status;
	private String windStr;
}
