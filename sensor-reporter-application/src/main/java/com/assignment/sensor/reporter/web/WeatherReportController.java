package com.assignment.sensor.reporter.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.sensor.reporter.exception.RecordNotFoundException;
import com.assignment.sensor.reporter.model.WeatherReportEntity;
import com.assignment.sensor.reporter.service.WeatherReportService;

@RestController
@RequestMapping("/weatherreport")
public class WeatherReportController {

	@Autowired
	private WeatherReportService service;
	
	/**
	 * All the Weather Reports in the Databases are returned
	 * @return ResponseEntity
	 */
	@GetMapping
	public ResponseEntity<List<WeatherReportEntity>> getAllWeatherReports() {
		List<WeatherReportEntity> list = service.getAllWeatherReports();
		return new ResponseEntity<List<WeatherReportEntity>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	/**
	 * Temperature Weather Report is returned
	 * @return ResponseEntity
	 * @throws RecordNotFoundException
	 */
	@GetMapping("temperature/average")
	public ResponseEntity<Object>  getTemperatureAverge() throws RecordNotFoundException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("AverageTemperature", service.getTemperatureAverage());
		return new ResponseEntity<Object>(map, new HttpHeaders(), HttpStatus.OK);
	}

	/**
	 * Creates a record of the weather report in the database
	 * @param weather
	 * @return ResponseEntity
	 * @throws RecordNotFoundException
	 */
	@PostMapping
	public ResponseEntity<WeatherReportEntity> createWeatherRecord(@RequestBody WeatherReportEntity weather)
			throws RecordNotFoundException {
		WeatherReportEntity updated = service.createWeatherRecord(weather);
		return new ResponseEntity<WeatherReportEntity>(updated, new HttpHeaders(), HttpStatus.CREATED);
	}
}