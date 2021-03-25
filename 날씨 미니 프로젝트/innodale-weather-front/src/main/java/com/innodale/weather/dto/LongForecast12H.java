package com.innodale.weather.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LongForecast12H {
	private String longInitDt;
	private String fcstDt;
	private int nx;
	private int ny;
	private Integer rainProb12h;
	private int temp12h;
	private float rain12h;
	private String sky;
	private int bodyTemp12h;
	private int hum12h;
	private int windDir;
	private float windSpeed;
	private String status;
	private String windStr;
}
