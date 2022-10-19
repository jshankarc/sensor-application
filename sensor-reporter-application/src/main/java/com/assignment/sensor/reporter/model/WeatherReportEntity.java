package com.assignment.sensor.reporter.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * Weather Report Database Entity
 *
 */
@Getter
@Setter
@Entity
@Table(name = "TBL_WEATHER_REPORT")
public class WeatherReportEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "temperature")
	private float temperature;

	@Column(name = "wind_speed")
	private float windSpeed;

	@Column(name = "humidity")
	private float humidity;

	@Column(name = "date_time")
	private String dateTime;
}