package com.innodale.weather.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ObsWeather {
	private String obsDt;
	private int nx;
	private int ny;
	private int temp;
	private float rain;
	private int bodyTemp;
	private int maxTemp;
	private int minTemp;
	private float sumRain;
	private String city;
	private String district;
}
