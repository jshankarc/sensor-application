package com.assignment.sensor.reporter.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.assignment.sensor.reporter.model.WeatherReportEntity;
 
@Repository
public interface WeatherReportRepository
        extends JpaRepository<WeatherReportEntity, Long> {
	/**
	 * Execute SQL query to fetch average temperature
	 * @return Integer - average temperature
	 */
	@Query(value = "SELECT AVG(temperature) FROM TBL_WEATHER_REPORT", nativeQuery = true)
	Optional<Integer> findAverageTemperature();
 
}
