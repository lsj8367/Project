package com.innodale.weather.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GeoLocation {
	private String code;
	private String city;
	private String district;
	private String dong;
	private int nx;
	private int ny;
	private float lat;
	private float lon;
}
