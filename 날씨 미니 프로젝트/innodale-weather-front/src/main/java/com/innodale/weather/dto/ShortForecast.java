package com.innodale.weather.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ShortForecast {
	private String shortInitDt;
	private String fcstDt;
	private int nx;
	private int	ny;
	private float temp1h;
	private float rain1h;
	private String sky;
	private int bodytemp1h;
	private int mm;
	private int dd;
	private String status;
	private int maxTemp;
	private int minTemp;
	private float sumRain;
}
