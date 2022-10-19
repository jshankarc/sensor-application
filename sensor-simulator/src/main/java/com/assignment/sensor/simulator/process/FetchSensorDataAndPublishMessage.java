package com.assignment.sensor.simulator.process;

import java.io.FileReader;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonObject;
import com.opencsv.CSVReader;

@Component
public class FetchSensorDataAndPublishMessage implements CommandLineRunner {
	
	@Value("${csv.file.path}")
	private String filePath;
	
	@Value("${weather.report.url}")
	private String weatherReportPath;

	@Autowired
	private RestTemplate restTemplate;

	/**
	 * reads CSV file 
	 * Delay for 5 seconds - This is to simulate sensor which posts messages periodically
	 * Invokes weather report API
	 */
	@Override
	public void run(String... args) throws Exception {
		try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
			String[] lineInArray;
			while ((lineInArray = reader.readNext()) != null) {
				TimeUnit.SECONDS.sleep(5);
				JsonObject json = formJsonRequest(lineInArray);
				postToWeatherReportApi(json);
			}
		}
	}

	/**
	 * Responsible for making Weather report API call
	 * @param JsonObject API message body 
	 */
	private void postToWeatherReportApi(JsonObject json) {
		// Wraps HTTP headers and Body
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");
		HttpEntity<JsonObject> requestEntity = new HttpEntity(json.toString(), headers);

		// posts HTTP request
		restTemplate.exchange(weatherReportPath, HttpMethod.POST, requestEntity,
				JsonObject.class);
	}

	/**
	 * Builds JSON request 
	 * @param eachRows A row in a CSV file
	 * @return JsonObject API message body
	 */
	private JsonObject formJsonRequest(String[] eachRows) {
		JsonObject json = new JsonObject();
		json.addProperty("dateTime", eachRows[0]);
		json.addProperty("temperature", Float.parseFloat(eachRows[1]));
		json.addProperty("humidity", Float.parseFloat(eachRows[2]));
		json.addProperty("windSpeed", Float.parseFloat(eachRows[3]));
		return json;
	}
}
