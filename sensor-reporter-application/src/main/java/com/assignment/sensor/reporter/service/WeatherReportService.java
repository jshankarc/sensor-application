package com.assignment.sensor.reporter.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.sensor.reporter.exception.RecordNotFoundException;
import com.assignment.sensor.reporter.model.WeatherReportEntity;
import com.assignment.sensor.reporter.repository.WeatherReportRepository;

@Service
public class WeatherReportService {

	@Autowired
	private WeatherReportRepository repository;

	/**
	 * Responsible of fetching and error handling 
	 * @return A list of {@link WeatherReportEntity}s are returned after fetching from the Database
	 */
	public List<WeatherReportEntity> getAllWeatherReports() {
		List<WeatherReportEntity> weather = repository.findAll();
		if (weather.size() > 0) {
			return weather;
		} else {
			return new ArrayList<WeatherReportEntity>();
		}
	}
	
	/**
	 * Responsible to invoke Repository API call for fetching average Temperature
	 * @return Integer
	 * @throws RecordNotFoundException
	 */

	public Integer getTemperatureAverage() throws RecordNotFoundException {
		Optional<Integer> avgTemperature = repository.findAverageTemperature();

		if (avgTemperature.isPresent()) {
			return avgTemperature.get();
		} else {
			throw new RecordNotFoundException("No weather report found");
		}
	}
	
	/**
	 * Saves {@link WeatherReportEntity} to the Database using helper Repository API class
	 * @param entity
	 * @return
	 * @throws RecordNotFoundException
	 */
	public WeatherReportEntity createWeatherRecord(WeatherReportEntity entity) throws RecordNotFoundException {
		return repository.save(entity);
	}
}